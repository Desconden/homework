package com.example.homework.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.homework.ui.theme.HomeworkTheme
import com.example.homework.ui.HomeworkApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeworkTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = Color.DarkGray) {
                    HomeworkApp()
                }
            }
        }
    }
}