package com.example.homework.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.homework.data.entity.Activity
import com.example.homework.data.entity.User

@Database(
    entities = [User::class, Activity::class],
    version = 9,
    exportSchema = false
)
abstract class App : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun activityDao(): ActivityDao
}
