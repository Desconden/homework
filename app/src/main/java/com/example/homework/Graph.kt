package com.example.homework

import android.content.Context
import androidx.room.Room
import com.example.homework.data.reprository.ActivityRepository
import com.example.homework.data.room.App

object Graph {

    lateinit var db: App

    lateinit var appContext: Context

    val activityRepository by lazy {
        ActivityRepository(
            activityDao = db.activityDao()
        )
    }

    fun provide(context: Context) {
        appContext = context
        db = Room.databaseBuilder(context, App::class.java, "HWDatabase.db")
            .fallbackToDestructiveMigration() // don't use this in production app
            .build()
    }


}