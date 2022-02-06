package com.example.homework

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class HomeworkAppState(
    val navController: NavHostController
) {
    fun navigateBack() {
        navController.popBackStack()
    }
}

@Composable
fun rememberHomeworkAppState(
    navController: NavHostController = rememberNavController()
) = remember(navController) {
    HomeworkAppState(navController)
}