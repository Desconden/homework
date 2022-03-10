package com.example.homework.ui.update

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.homework.Graph
import com.example.homework.R
import com.example.homework.data.entity.Activity
import com.google.accompanist.insets.systemBarsPadding
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch
import java.util.*

@Composable
fun Update(
    onBackPress: () -> Unit,
    navController: NavController,
    activityId: String?
) {
    val activityID = activityId?.toLong()
    val coroutineScope = rememberCoroutineScope()
    val viewModel: UpdateViewModel = viewModel()
    val calendar = Calendar.getInstance()
    val hour = calendar[Calendar.HOUR_OF_DAY]
    val minute = calendar[Calendar.MINUTE]

    val timePicked = remember { mutableStateOf("") }
    val timePickerDialog = TimePickerDialog(
        Graph.appContext,
        {_, hour : Int, minute: Int ->
            timePicked.value = "$hour:$minute"
        }, hour, minute, false
    )
    val year: Int
    val month: Int
    val day: Int

    val cal = Calendar.getInstance()
    year = cal.get(Calendar.YEAR)
    month = cal.get(Calendar.MONTH)
    day = cal.get(Calendar.DAY_OF_MONTH)
    cal.time = Date()

    val date = remember { mutableStateOf("") }
    val datePickerDialog = DatePickerDialog(
        Graph.appContext,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            date.value = "$dayOfMonth/$month/$year"
        }, year, month, day
    )

    /*coroutineScope.launch {
        val CurrentActivity = viewModel.getActivity(activityID)
    }*/
    val latLng = navController
        .currentBackStackEntry
        ?.savedStateHandle
        ?.getLiveData<LatLng>("location_data")
        ?.value
    TopAppBar(
        title = {
            Text(
                text = "Update",
                color = Color.White,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .heightIn(max = 24.dp)
            )
        },
        backgroundColor = backgroundColor,
        actions = {
            IconButton(onClick = {
                coroutineScope.launch {
                    viewModel.deleteActivity(activityID!!)
                }
                onBackPress()
            }) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = stringResource(R.string.search)
                )
            }
        }
    )
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
                IconButton(onClick = {
                    coroutineScope.launch {
                        viewModel.deleteActivity(activityID!!)
                    }
                    onBackPress()
                }) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = stringResource(R.string.search)
                    )
                }
                Text(activityID.toString())
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
                Spacer(modifier = Modifier.height(10.dp))
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
                //time picker
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth()
                ){
                    Button(
                        modifier = Modifier.fillMaxWidth(0.5f),
                        onClick = {
                        timePickerDialog.show()
                    }) {
                        Text(text = "Open Time Picker")
                    }
                }

                // date picker
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                    datePickerDialog.show()
                }) {
                    Text(text = "Open Date Picker")
                }
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
                    Spacer(modifier = Modifier.height(10.dp))
                    if (latLng == null) {
                        OutlinedButton(
                            onClick = { navController.navigate("map") },
                            modifier = Modifier.height(55.dp)
                        ) {
                            Text(text = "Activity Location")
                        }
                    } else {
                        Text(
                            text = "Lat: ${latLng.latitude}, \nLng: ${latLng.longitude}"
                        )
                    }
                    val lat = latLng?.latitude.toString()
                    val lon = latLng?.longitude.toString()
                    //time picker
                    Spacer(modifier = Modifier.height(10.dp))
                    Row {
                        /*fun onCreate(savedInstanceState: Bundle?) {
                        onCreate(savedInstanceState)
                        OnClickTime()
                    }*/
                        Spacer(modifier = Modifier.height(10.dp))
                        val UpdatedActivity = Activity(
                            activityId = activityID!!,
                            activityCategory = type.value,
                            activityDesc = description.value,
                            activityTitle = name.value,
                            activityDate = Date().time,
                            activityTime = time.value,
                            activitylatitude = lat,
                            activitylongitude = lon
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
    }

