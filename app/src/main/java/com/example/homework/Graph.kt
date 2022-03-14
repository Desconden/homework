package com.example.homework

import android.content.Context
import androidx.room.Room
import com.example.homework.data.reprository.ActivityRepository
import com.example.homework.data.reprository.UserRepository
import com.example.homework.data.room.App
import com.google.android.gms.maps.model.LatLng

object Graph {

    lateinit var db: App

    lateinit var appContext: Context

    lateinit var location: LatLng

    val activityRepository by lazy {
        ActivityRepository(
            activityDao = db.activityDao()
        )
    }
    val userRepository by lazy {
        UserRepository(
            userDao = db.userDao()
        )
    }

    fun provide(context: Context) {
        appContext = context
        db = Room.databaseBuilder(context, App::class.java, "HWDatabase.db")
            .fallbackToDestructiveMigration() // don't use this in production app
            .build()
    }


}