package com.example.homework.data.reprository

import com.example.homework.data.entity.Activity
import com.example.homework.data.room.ActivityDao
import kotlinx.coroutines.flow.Flow

class ActivityRepository(
    private val activityDao: ActivityDao
) {
suspend fun addActivity(activity: Activity) = activityDao.insertActivity(activity)

    fun getActivity() : Flow<List<Activity>> {
        return activityDao.getAllActivity()
    }
}