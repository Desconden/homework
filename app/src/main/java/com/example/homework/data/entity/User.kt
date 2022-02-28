package com.example.homework.data.entity

import android.graphics.Bitmap
import androidx.room.*
import com.example.homework.data.room.ActivityDao
import com.example.homework.data.room.UserDao

@Entity (
    tableName = "Users",
    indices = [
        Index(value = ["user_id", "user_Mail", "user_Name", "user_Password"],  unique = true)
    ]
        )
    data class User(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "user_id") val id: Long = 0,
    @ColumnInfo(name = "user_Name") val userName: String,
    @ColumnInfo(name = "user_Mail") val userMail: String,
    @ColumnInfo(name = "user_Password") val userPassword: Double
    )

