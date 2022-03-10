package com.example.homework.ui.home.mapview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework.Graph
import com.example.homework.data.entity.Activity
import com.example.homework.data.reprository.ActivityRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeMapViewModel(
    private val activityRepository: ActivityRepository = Graph.activityRepository
):ViewModel() {
    private val _state = MutableStateFlow(HomeMapViewState())

    val state: StateFlow<HomeMapViewState>
        get() = _state
    init {
        viewModelScope.launch {
            activityRepository.getActivity().collect { list ->
                _state.value = HomeMapViewState(
                    activity = list
                )
            }
        }
    }
}
data class HomeMapViewState(
    val activity: List<Activity> = emptyList()
)
