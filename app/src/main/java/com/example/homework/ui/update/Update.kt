package com.example.homework.ui.update

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.homework.data.entity.Activity
import com.example.homework.ui.home.HomeActivity.HomeActivityViewModel
import com.example.homework.util.viewModelProviderFactoryOf
import com.google.accompanist.insets.systemBarsPadding
import kotlinx.coroutines.launch
import java.util.*
fun Update(){

}
/*
@Composable
fun Update(
    activityID: Long,
    onBackPress: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val viewModel: UpdateViewModel = viewModel(
        key = "activity_list_$activityID",
        factory = viewModelProviderFactoryOf { HomeActivityViewModel(activityID)
        }
    )
    coroutineScope.launch {
        val CurrentActivity = viewModel.getActivity(activityID)
    }

    val name = rememberSaveable { mutableStateOf("") }
    val type = rememberSaveable { mutableStateOf("") }
    val description = rememberSaveable { mutableStateOf("") }
    val time = rememberSaveable { mutableStateOf("") }
    val activitydate = rememberSaveable { mutableStateOf("") }

    Surface {
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
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.padding(16.dp)
            ) {
                OutlinedTextField(
                    value = name.value,
                    onValueChange = { data -> name.value = data },
                    label = { Text("Activity Name") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    )

                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = type.value,
                    onValueChange = { data -> type.value = data },
                    label = { Text("Activity Type") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    )
                )
                OutlinedTextField(
                    value = time.value,
                    onValueChange = { data -> time.value = data },
                    label = { Text("Time") },
                    modifier = Modifier
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = description.value,
                    onValueChange = { data -> description.value = data },
                    label = { Text("Description") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    )
                )
                //time picker
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    /*fun onCreate(savedInstanceState: Bundle?) {
                        onCreate(savedInstanceState)
                        OnClickTime()
                    }*/
                    Spacer(modifier = Modifier.height(10.dp))
                    val UpdatedActivity = Activity(
                        activityId = activityID,
                        activityCategory = type.value,
                        activityDesc = description.value,
                        activityTitle = name.value,
                        activityDate = Date().time,
                        activityTime = time.value
                    )
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                viewModel.updateActivity(
                                    UpdatedActivity
                                )
                            }
                            onBackPress()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(55.dp)
                    ) {
                        Text("Update Activity")
                    }
                }
            }
        }

    }
}*/