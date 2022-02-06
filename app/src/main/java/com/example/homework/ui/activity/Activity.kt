package com.example.homework.ui.activity

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.systemBarsPadding

@Composable
fun Activity(
    onBackPress: () -> Unit
){
    Surface {
        val name = rememberSaveable { mutableStateOf("") }
        val type = rememberSaveable { mutableStateOf("") }
        val date = rememberSaveable { mutableStateOf("") }
        val description = rememberSaveable { mutableStateOf("") }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .systemBarsPadding()
        ) {
            TopAppBar {
                IconButton(
                    onClick = onBackPress
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null
                    )
                }
                Text("Activity")
            }
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.padding(16.dp)
            ){
                OutlinedTextField(
                    value = name.value,
                    onValueChange = { data -> name.value = data},
                    label = { Text("Activity Name") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    )

                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = type.value,
                    onValueChange = { data -> type.value = data},
                    label = { Text("Activity Type") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = description.value,
                    onValueChange = { data -> description.value = data},
                    label = { Text("Description") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row{
                    OutlinedTextField(
                        value = date.value,
                        onValueChange = { data -> date.value = data},
                        label = { Text("Date") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text
                        )
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth().size(55.dp)
                ){
                    Text("Add Activity")
                }
            }
        }
    }
}