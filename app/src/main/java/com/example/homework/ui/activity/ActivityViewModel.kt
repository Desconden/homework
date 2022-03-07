package com.example.homework.ui.activity

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat.from
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.*
import com.example.homework.data.entity.Activity
import com.example.homework.data.reprository.ActivityRepository
import com.example.homework.ui.home.HomeActivity.toDateString
import com.example.homework.Graph
import com.example.homework.util.NotificationWorker
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class ActivityViewModel(
    private val activityRepository: ActivityRepository = Graph.activityRepository
) : ViewModel() {
    private val _state = MutableStateFlow(ActivityViewState())

    val state: MutableStateFlow<ActivityViewState>
        get() = _state

    suspend fun saveActivity(activity: Activity) {
        createActivityNotification(activity)
        reminderActivityNotification(activity)
        return activityRepository.addActivity(activity)
    }
    suspend fun noNotSaveActivity(activity: Activity) {
        return activityRepository.addActivity(activity)
    }

    init {
        createNotificationChannel(context = Graph.appContext)
        viewModelScope.launch {
            activityRepository.getActivity().collect { activity ->
                _state.value = ActivityViewState(activity)
            }
        }
        setOneTimeNotification()
        reminderNotification()
    }
}
private fun setOneTimeNotification(){
    val workManager = WorkManager.getInstance(Graph.appContext)
    val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED).build()
    val notificationWorker = OneTimeWorkRequestBuilder<NotificationWorker>()
        .setInitialDelay(5, TimeUnit.SECONDS)
        .setConstraints(constraints)
        .build()
    workManager.enqueue(notificationWorker)

    //monitoring the work
    workManager.getWorkInfoByIdLiveData(notificationWorker.id)
        .observeForever{ workInfo ->
            if (workInfo.state == WorkInfo.State.SUCCEEDED) {
                createSimpleNotification()
            }
        }
}
private fun reminderNotification(){
    val workManager = WorkManager.getInstance(Graph.appContext)
    val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED).build()
    val notificationWorker2 = OneTimeWorkRequestBuilder<NotificationWorker>()
        .setInitialDelay(30, TimeUnit.SECONDS)
        .setConstraints(constraints)
        .build()
    workManager.enqueue(notificationWorker2)
}
private fun createNotificationChannel(context : Context){
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
        val name = "NotificationChannelName"
        val descriptionText = "NotificationChannelDescText"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel("CHANNEL_ID", name, importance).apply {
            description = descriptionText
        }
        val notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}
private fun createSimpleNotification(){
    val notificationId = 1
    val builder = NotificationCompat.Builder(Graph.appContext, "CHANNEL_ID")
        .setSmallIcon(com.example.homework.R.drawable.ic_launcher_background)
        .setContentTitle("Welcome!")
        .setContentText("This is HW of MobileComputing.")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
    with(from(Graph.appContext)) {
        notify(notificationId, builder.build())
    }
}
private fun createActivityNotification(activity: Activity){
    val notificationId = 2
    val builder = NotificationCompat.Builder(Graph.appContext, "CHANNEL_ID")
        .setSmallIcon(com.example.homework.R.drawable.ic_launcher_background)
        .setContentTitle("Activity Addded.")
        .setContentText("${activity.activityTitle} of ${activity.activityCategory} on ${activity.activityDate.toDateString()}")
        .setPriority(NotificationCompat.PRIORITY_HIGH)
    with(from(Graph.appContext)){
        notify(notificationId, builder.build())
    }
}
private fun reminderActivityNotification(activity: Activity){
    val notificationId = 3
    val builder = NotificationCompat.Builder(Graph.appContext, "CHANNEL_ID")
        .setSmallIcon(com.example.homework.R.drawable.ic_launcher_background)
        .setContentTitle("Last activity.")
        .setContentText("You saved ${activity.activityTitle} just now make sure you don't forget.")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .setStyle(NotificationCompat.BigTextStyle()
            .bigText(activity.activityDesc))
    with(from(Graph.appContext)){
        notify(notificationId, builder.build())
    }
}
data class ActivityViewState(
    val activity: List<Activity> = emptyList()
)