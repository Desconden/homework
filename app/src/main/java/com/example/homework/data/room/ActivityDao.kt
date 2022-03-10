package com.example.homework.data.room

import androidx.room.*
import com.example.homework.data.entity.Activity

@Dao
abstract class ActivityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertActivity(activity: Activity)
    @Delete
    abstract suspend fun deleteActivity(activity: Activity)
    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun updateActivity(activity: Activity)

    @Query("SELECT * FROM ACTIVITY WHERE activity_id = :activityID")
    abstract fun getActivityByID(activityID: Long): Activity

    @Query("DELETE FROM activity")
    abstract fun deleteAllActivity()

    @Query("SELECT * FROM ACTIVITY LIMIT 15")
    abstract fun getAllActivity(): kotlinx.coroutines.flow.Flow<List<Activity>>

}