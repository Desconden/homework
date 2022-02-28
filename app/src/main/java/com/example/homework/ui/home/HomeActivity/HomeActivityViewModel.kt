package com.example.homework.ui.home.HomeActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework.data.entity.Activity
import com.example.homework.data.reprository.ActivityRepository
import com.example.homework.data.room.ActivityDao
import com.example.homework.ui.activity.Activity
import com.example.homework.util.Graph
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.*

class HomeActivityViewModel(
    private val activityRepository: ActivityRepository = Graph.activityRepository
) : ViewModel() {
    private val _state = MutableStateFlow(HomeActivityViewState())

    val state: StateFlow<HomeActivityViewState>
        get() = _state

    init {
        viewModelScope.launch {
            activityRepository.getActivity().collect { list ->
                _state.value = HomeActivityViewState(
                    activity = list
                )
            }
        }
    }
}

data class HomeActivityViewState(
    val activity: List<Activity> = emptyList()
)