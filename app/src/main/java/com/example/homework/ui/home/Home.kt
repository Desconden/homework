package com.example.homework.ui.home

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.homework.R
import com.example.homework.ui.home.HomeActivity.HomeActivity
import com.google.accompanist.insets.systemBarsPadding

@Composable
fun Home(
    viewModel: HomeViewModel = viewModel(),
    navController: NavController
){
HomeContent(
    navController = navController
    )
}
@Composable
fun HomeContent(
    navController: NavController
) {
    Scaffold(
        modifier = Modifier.padding(bottom = 24.dp),
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(route = "activity") },
                contentColor = Color.Green,
                modifier = Modifier.padding(all = 20.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .systemBarsPadding()
                .fillMaxWidth()
        ) {
            val appBarColor = Color.LightGray

            HomeAppBar(
                backgroundColor = appBarColor,
            )
            HomeActivity(
                modifier = Modifier.fillMaxSize(),
                navController = navController
            )
        }
    }
}
@Composable
private fun HomeAppBar(
    backgroundColor: Color
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                color = Color.White,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .heightIn(max = 24.dp)
            )
        },
        backgroundColor = backgroundColor,
        actions = {
            IconButton( onClick = {} ) {
                Icon(imageVector = Icons.Filled.LocationOn, contentDescription = stringResource(R.string.search))
            }
            IconButton( onClick = {} ) {
                Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = stringResource(R.string.account))
            }
        }
    )
}
