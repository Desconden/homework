package com.example.homework

import android.app.Application
import com.example.homework.util.Graph

class HWApplication: Application() {
    override fun onCreate(){
        super.onCreate()
        Graph.provide(this)
    }
}