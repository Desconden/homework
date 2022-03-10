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
import androidx.lifecycle.viewmodel.compose.viewModel
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

                    setMapLongClick(map = map)
                }
            }
        }
    }

    private fun setMapLongClick(
        map: GoogleMap
    ) {
        map.setOnMapLongClickListener { latlng ->
            val snippet = String.format(
                Locale.getDefault(),
                "Lat: %1$.2f, Lng: %2$.2f",
                latlng.latitude,
                latlng.longitude
            )

            map.addMarker(
                MarkerOptions().position(latlng).title("Activity location").snippet(snippet)
            ).apply {

            }
        }
    }