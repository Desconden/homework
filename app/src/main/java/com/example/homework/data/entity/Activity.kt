package com.example.homework.data.entity

import androidx.room.*

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
        @ColumnInfo(name = "activity_RDate") var activityRDate: String,
        @ColumnInfo(name = "activity_Desc") var activityDesc: String,
        @ColumnInfo(name = "activity_latitude") var activitylatitude: String,
        @ColumnInfo(name = "activitiy_longitude") var activitylongitude: String
)
       /* @Embedded
        var activityLocation: Location

    )

class Location(longitude: String, latitude: String) {
        var longitude: String = ""
                var latitude: String = ""
}
*/