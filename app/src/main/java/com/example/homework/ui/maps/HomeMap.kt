package com.example.homework.ui.home.mapview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.homework.Graph
import com.example.homework.data.entity.Activity
import com.example.homework.util.rememberMapViewWithLifecycle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.ktx.awaitMap
import kotlinx.coroutines.launch
import java.util.*

@Composable
fun HomeMap(
    onBackPress: () -> Unit,
    ){
        val mapView = rememberMapViewWithLifecycle()
        val coroutineScope = rememberCoroutineScope()
        val viewModel: HomeMapViewModel = viewModel()
        val viewState by viewModel.state.collectAsState()
        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(bottom = 36.dp)
        ) {
            AndroidView({mapView}) { mapView ->
                coroutineScope.launch {
                    val map = mapView.awaitMap()
                    map.uiSettings.isZoomControlsEnabled = true
                    val location = LatLng(65.06, 25.47)

                    map.moveCamera(
                        CameraUpdateFactory.newLatLngZoom(location, 10f)
                    )

                    val markerOptions = MarkerOptions()
                        .title("Welcome to Oulu")
                        .position(location)
                    map.addMarker(markerOptions)
                    setMapLongClick(map = map, list = viewState.activity)
                }
            }
        }
    }

    private fun setMapLongClick(
        map: GoogleMap,
        list: List<Activity>
    ) {
        map.setOnMapLongClickListener { latlng ->
            val snippet = String.format(
                Locale.getDefault(),
                "Lat: %1$.2f, Lng: %2$.2f",
                latlng.latitude,
                latlng.longitude
            )
            map.addMarker(
                MarkerOptions().position(latlng).title("Device Location").snippet(snippet)
            ).apply {
                for (activity in list) {
                    val lon = activity.activitylongitude.toDouble()
                    val lan = activity.activitylatitude.toDouble()
                    val loc = LatLng(lan, lon)
                    if (lon - 0.03 < latlng.longitude && latlng.longitude < lon + 0.03
                        && lan - 0.03 < latlng.latitude && latlng.latitude < lan + 0.03 ) {
                            if(lon - 0.01 < latlng.longitude && latlng.longitude < lon + 0.01
                                && lan - 0.01 < latlng.latitude && latlng.latitude < lan + 0.01) {
                                createSimpleNotification(activity.activityTitle,
                                    activity.activityDesc,activity.activityTime, activity.activityRDate)
                            }
                        val markers = MarkerOptions()
                            .title(activity.activityTitle)
                            .position(loc)
                        map.addMarker(markers)
                    }
                }
            }
        }
    }
private fun createSimpleNotification(title: String, desc: String, time: String,date: String){
    val notificationId = 8
    val builder = NotificationCompat.Builder(Graph.appContext, "CHANNEL_ID")
        .setSmallIcon(com.example.homework.R.drawable.ic_launcher_background)
        .setContentTitle("You are close to The $title!")
        .setContentText("Time of the Reminder:$time")
        .setStyle(NotificationCompat.BigTextStyle()
            .bigText("Date of the Reminder:$date, $desc"))
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
    with(NotificationManagerCompat.from(Graph.appContext)) {
        notify(notificationId, builder.build())
    }
}
