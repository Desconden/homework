package com.example.homework.ui

import androidx.compose.runtime.Composable
import androidx.navigation.activity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.homework.HomeworkAppState
import com.example.homework.rememberHomeworkAppState
import com.example.homework.ui.activity.Activity
import com.example.homework.ui.home.Home
import com.example.homework.ui.login.Login
import com.example.homework.ui.register.Register

@Composable
fun HomeworkApp(
    appState: HomeworkAppState = rememberHomeworkAppState()
) {
    NavHost(
        navController = appState.navController,
        startDestination = "login"
    ) {
        composable(route = "login"){
            Login(navController = appState.navController)
        }
        composable(route = "home"){
            Home(navController = appState.navController)
        }
        composable(route = "activity"){
            Activity(onBackPress = appState::navigateBack)
        }
        composable(route = "register"){
            Register(navController = appState.navController)
        }
    }


}