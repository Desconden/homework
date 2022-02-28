package com.example.homework.data.entity

import androidx.room.*
import java.util.*

@Entity(tableName = "activity",
        indices = [
                Index("activity_id", unique = true)
        ])
data class Activity(
        @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "activity_id") var activityId: Long,
        @ColumnInfo(name = "activity_Title") var activityTitle: String,
        @ColumnInfo(name = "activity_Category") var activityCategory: String,
        @ColumnInfo(name = "activity_Date") var activityDate: Long,
        @ColumnInfo(name = "activity_Time") var activityTime: String,
        @ColumnInfo(name = "activity_Desc") var activityDesc: String
    )
