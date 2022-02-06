package com.example.homework.ui.register

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.accompanist.insets.systemBarsPadding
import kotlinx.coroutines.coroutineScope
import kotlin.coroutines.coroutineContext

@Composable
fun Register(
    navController: NavController
){
    Surface(modifier = Modifier.fillMaxSize()) {
        val email = rememberSaveable { mutableStateOf("")}
        val username = rememberSaveable { mutableStateOf("") }
        val password = rememberSaveable { mutableStateOf("") }
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .systemBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            OutlinedTextField(
                value = email.value,
                onValueChange = { email.value = it},
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = username.value,
                onValueChange = { username.value = it},
                label = { Text("Username") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {
                          /*coroutineScope.launch{
                              viewModel.RegisterUser(
                                  com.example.homework.data.entity.User(
                                      userName = username.value,
                                      userMail = email.value,
                                      userPassword = password.value.toDouble()
                                  )
                              )
                          }*/
                    navController.navigate("login")
                },
                enabled = true,
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.large
            ) {
                Text(text = "Register")
            }
        }
    }
}