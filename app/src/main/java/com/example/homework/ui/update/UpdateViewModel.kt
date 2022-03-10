package com.example.homework.ui.update

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.homework.Graph
import com.example.homework.data.entity.Activity
import com.example.homework.data.reprository.ActivityRepository
import com.example.homework.ui.home.HomeActivity.HomeActivityViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class UpdateViewModel(
    private val activityRepository: ActivityRepository = Graph.activityRepository
): ViewModel() {
    //listelemeyi ekle
    //private val _state = MutableStateFlow(UpdateViewState())

    //val state: MutableStateFlow<UpdateViewState>
    //   get() = _state

    suspend fun updateActivity(activity: Activity){
        return activityRepository.editActivity(activity)
    }
    suspend fun getActivity(activityID: Long): Activity {
        return activityRepository.getActivityByID(activityID)
    }
    suspend fun deleteActivity(activityID: Long){
        viewModelScope.launch {
            val act = getActivity(activityID)
            activityRepository.deleteActivity(act)
        }
    }
}
data class UpdateViewState(
    val activity: Activity
)