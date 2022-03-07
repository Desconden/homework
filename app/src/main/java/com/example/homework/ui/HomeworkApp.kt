package com.example.homework.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.example.homework.ui.activity.Activity
import com.example.homework.ui.home.Home
import com.example.homework.ui.home.HomeActivity.HomeActivityViewModel
import com.example.homework.ui.login.Login
import com.example.homework.ui.register.Register
import com.example.homework.ui.theme.HomeworkAppState
import com.example.homework.ui.theme.rememberHomeworkAppState
import com.example.homework.ui.update.Update
import com.example.homework.ui.update.UpdateViewModel
import com.example.homework.util.viewModelProviderFactoryOf

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
       // composable(route = "update"){
       //     Update(onBackPress = appState::navigateBack)
       // }
    }


}