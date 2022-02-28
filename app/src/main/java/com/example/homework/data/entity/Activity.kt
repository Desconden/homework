package com.example.homework.data.entity

import androidx.room.*
import java.util.*

@Entity(tableName = "activity",
        indices = [
                Index("activity_id", unique = true)
        ])
data class Activity(
        @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "activity_id") val activityId: Long,
        @ColumnInfo(name = "activity_Title") val activityTitle: String,
        @ColumnInfo(name = "activity_Category") val activityCategory: String,
        @ColumnInfo(name = "activity_Date") val activityDate: Long,
        @ColumnInfo(name = "activity_Time") val activityTime: String,
        @ColumnInfo(name = "activity_Desc") val activityDesc: String
    )
