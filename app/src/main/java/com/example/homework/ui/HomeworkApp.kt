package com.example.homework.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.activity
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.homework.ui.activity.Activity
import com.example.homework.ui.home.Home
import com.example.homework.ui.home.mapview.HomeMap
import com.example.homework.ui.login.Login
import com.example.homework.ui.maps.ActivityLocationMap
import com.example.homework.ui.register.Register
import com.example.homework.ui.theme.HomeworkAppState
import com.example.homework.ui.theme.rememberHomeworkAppState
import com.example.homework.ui.update.Update

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
            Activity(onBackPress = appState::navigateBack, navController = appState.navController)
        }
        composable(route = "register"){
            Register(navController = appState.navController)
        }
        composable("map"){
            ActivityLocationMap(navController = appState.navController)
        }
        composable(route = "update/{Id}",
        ){
            val activityId = it.arguments?.getString("Id")
            Update(onBackPress = appState::navigateBack, navController = appState.navController, activityId = activityId)
        }
        composable("HomeMap"){
            HomeMap(onBackPress = appState::navigateBack)
        }
    }


}