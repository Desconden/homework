package com.example.homework.ui.activity

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.homework.data.entity.Location
import com.google.accompanist.insets.systemBarsPadding
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch
import java.util.*

@Composable
fun Activity(
    onBackPress: () -> Unit,
    viewModel: ActivityViewModel = viewModel(),
    navController: NavController
) {
    val coroutineScope = rememberCoroutineScope()
    val name = rememberSaveable { mutableStateOf("") }
    val type = rememberSaveable { mutableStateOf("") }
    val description = rememberSaveable { mutableStateOf("") }
    val time = rememberSaveable { mutableStateOf("") }

    val latLng = navController
        .currentBackStackEntry
        ?.savedStateHandle
        ?.getLiveData<LatLng>("location_data")
        ?.value
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
                //Location
                Spacer(modifier = Modifier.height(10.dp))
                if (latLng == null) {
                    OutlinedButton(
                        onClick = { navController.navigate("map") },
                        modifier = Modifier.height(55.dp)
                    ) {
                        Text(text = "Activity Location")
                    }
                }
                else {
                    Text(
                        text = "Lat: ${latLng.latitude}, \nLng: ${latLng.longitude}"
                    )
                }
                val lat = latLng?.latitude
                val lon = latLng?.longitude

                //time picker
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    /*fun onCreate(savedInstanceState: Bundle?) {
                        onCreate(savedInstanceState)
                        OnClickTime()
                    }*/
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                viewModel.saveActivity(
                                    com.example.homework.data.entity.Activity(
                                        activityTitle = name.value,
                                        activityDesc = description.value,
                                        activityCategory = type.value,
                                        activityDate = Date().time,
                                        activityTime = time.value,
                                        activityLocation = Location(lon, lat)

                                    )
                                )
                            }
                            onBackPress()
                        },
                        modifier = Modifier
                            .fillMaxWidth(fraction = 0.5f)
                            .size(55.dp)
                    ) {
                        Text("Add Activity")
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                viewModel.noNotSaveActivity(
                                    com.example.homework.data.entity.Activity(
                                        activityTitle = name.value,
                                        activityDesc = description.value,
                                        activityCategory = type.value,
                                        activityDate = Date().time,
                                        activityTime = time.value,
                                        activityLocation = Location(lon, lat)
                                    )
                                )
                            }
                            onBackPress()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(55.dp)
                    ) {
                        Text("No Notification")
                    }
                }
            }
        }

    }
}

/*
private fun OnClickTime() {

    val textView = TextView(null)
    val timePicker = TimePicker(null)
    timePicker.setOnTimeChangedListener { _, hour, minute ->
        var hour = hour
        var am_pm = ""
        // AM_PM decider logic
        when {
            hour == 0 -> {
                hour += 12
                am_pm = "AM"
            }
            hour == 12 -> am_pm = "PM"
            hour > 12 -> {
                hour -= 12
                am_pm = "PM"
            }
            else -> am_pm = "AM"
        }
        if (textView != null) {
            val hour = if (hour < 10) "0" + hour else hour
            val min = if (minute < 10) "0" + minute else minute
            // display format of time
            val msg = "Time is: $hour : $min $am_pm"
            textView.text = msg
            textView.visibility = ViewGroup.VISIBLE
        }
    }
}
*/