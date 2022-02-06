package com.example.homework.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity (
    tableName = "User",
    indices = [
        Index("id", unique = true)
    ]
        )
    data class User(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "userName") val userName: String,
    @ColumnInfo(name = "userMail") val userMail: String,
    @ColumnInfo(name = "userPassword") val userPassword: Double,
    )
