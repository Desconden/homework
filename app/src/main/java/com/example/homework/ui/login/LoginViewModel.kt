package com.example.homework.ui.login

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.*
import com.example.homework.Graph
import com.example.homework.util.NotificationWorker
import java.util.concurrent.TimeUnit


class ActivityViewModel() {

}

fun registerNotification(){
    val workManager = WorkManager.getInstance(Graph.appContext)
    val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED).build()
    val notificationWorker = OneTimeWorkRequestBuilder<NotificationWorker>()
        .setInitialDelay(5, TimeUnit.SECONDS)
        .setConstraints(constraints)
        .build()
    workManager.enqueue(notificationWorker)
}
fun NotiChannel(context : Context){
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
        val name = "NotificationChannelName"
        val descriptionText = "NotificationChannelDescText"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel("CHANNEL_ID2", name, importance).apply {
            description = descriptionText
        }
        val notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}
fun simpleRegisterNotification(){
    val notificationId = 6
    val builder = NotificationCompat.Builder(Graph.appContext, "CHANNEL_ID2")
        .setSmallIcon(com.example.homework.R.drawable.ic_launcher_background)
        .setContentTitle("Welcome!")
        .setContentText("Enter email to register")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
    with(NotificationManagerCompat.from(Graph.appContext)) {
        notify(notificationId, builder.build())
    }
}