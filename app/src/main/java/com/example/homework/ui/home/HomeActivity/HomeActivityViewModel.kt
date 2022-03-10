package com.example.homework.ui.home.HomeActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework.data.entity.Activity
import com.example.homework.data.reprository.ActivityRepository
import com.example.homework.Graph
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeActivityViewModel(
    //private val activityID: Long,
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
    fun deleteActivity(activityID: Long){
        viewModelScope.launch(Dispatchers.IO) {
            activityRepository.deleteActivity(activityRepository.getActivityByID(activityID))
        }
    }
    fun deleteAllActivity(){
        viewModelScope.launch {
            activityRepository.deleteAllActivity()
        }
    }
}

data class HomeActivityViewState(
    val activity: List<Activity> = emptyList()
)
