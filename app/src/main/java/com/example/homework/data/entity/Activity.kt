package com.example.homework.data.entity

import java.util.*

data class Activity(
        val activityId: Long,
        val activityTitle: String,
        val activityCategory: String,
        val activityDate: Date,
        val activityDesc: String
    )
