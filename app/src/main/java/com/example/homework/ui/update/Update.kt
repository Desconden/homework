package com.example.homework.ui.update

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.widget.DatePicker
import android.widget.Toast
import androidx.activity.ComponentActivity
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
import androidx.compose.ui.platform.LocalContext
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
    activityId: String?,
    title: String?,
    activityCat: String?,
    activityDesc: String?
) {
    var flag1 = 1
    var flag2 = 1
    val localContext = LocalContext.current as ComponentActivity
    val activityID = activityId?.toLong()
    val coroutineScope = rememberCoroutineScope()
    val viewModel: UpdateViewModel = viewModel()
    val calendar = Calendar.getInstance()
    val hour = calendar[Calendar.HOUR_OF_DAY]
    val minute = calendar[Calendar.MINUTE]
    val timePicked = remember { mutableStateOf("") }
    val timePickerDialog = TimePickerDialog(
        localContext,
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
        localContext,
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
    var name = rememberSaveable { mutableStateOf("$title") }
    var type = rememberSaveable { mutableStateOf("$activityCat") }
    var description = rememberSaveable { mutableStateOf("$activityDesc") }

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
                Text(title.toString())
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
                //time picker
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if(flag1 == 1) {
                        Button(
                            modifier = Modifier.fillMaxWidth(0.5f),
                            onClick = {
                                flag1 = 0
                                timePickerDialog.show()
                            }) {
                            Text(text = "Open Time Picker")
                        }
                    }
                    else {
                        Text(
                            text = timePicked.value,
                            modifier = Modifier.fillMaxWidth(0.5f)
                        )
                    }

                    if(flag2 == 1) {
                        // date picker
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                flag2 = 0
                                datePickerDialog.show()
                            }) {
                            Text(text = "Open Date Picker")
                        }
                    }
                    else {
                        Text(
                            text = date.value,
                            modifier = Modifier.fillMaxWidth(0.5f)
                        )
                    }
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
                            activityTime = timePicked.value,
                            activitylatitude = lat,
                            activitylongitude = lon,
                            activityRDate = date.value,
                        )
                        Button(
                            onClick = {
                                coroutineScope.launch {
                                    viewModel.updateActivity(
                                        UpdatedActivity
                                    )
                                }
                                Toast.makeText(localContext, "$title has been updated to " +
                                        "${name.value}", Toast.LENGTH_LONG).show()
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



