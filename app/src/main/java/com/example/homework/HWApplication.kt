package com.example.homework

import android.app.Application

class HWApplication: Application() {
    override fun onCreate(){
        super.onCreate()
        Graph.provide(this)
    }
}