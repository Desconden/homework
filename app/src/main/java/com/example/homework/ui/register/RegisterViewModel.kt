package com.example.homework.ui.register

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModel
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.homework.Graph
import com.example.homework.data.entity.User
import com.example.homework.util.NotificationWorker
import kotlinx.coroutines.flow.StateFlow
import java.util.concurrent.TimeUnit

class RegisterViewModel(
): ViewModel() {
}
/*
suspend fun saveUser(user: User): Long{
    return UserRepository.addUser(user)
}
*/