package com.example.homework.data.entity

import androidx.room.*
import java.util.*

@Entity(tableName = "activity",
        indices = [
                Index("activity_id", unique = true)
        ])
data class Activity(
        @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "activity_id") var activityId: Long = 0,
        @ColumnInfo(name = "activity_Title") var activityTitle: String,
        @ColumnInfo(name = "activity_Category") var activityCategory: String,
        @ColumnInfo(name = "activity_Date") var activityDate: Long,
        @ColumnInfo(name = "activity_Time") var activityTime: String,
        //@ColumnInfo(name = "activity_TDate") var activityTDate: String,
        //@ColumnInfo(name = "location_x") var activitylocx: Long,
        //@ColumnInfo(name = "location_y") var activitylocy: Long,
        @ColumnInfo(name = "activity_Desc") var activityDesc: String
    )
