package com.example.homework.ui.home.HomeActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework.data.entity.Activity
import com.example.homework.ui.activity.Activity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.*

class HomeActivityViewModel  : ViewModel(){
    private val _state = MutableStateFlow(HomeActivityViewState())
    val state: StateFlow<HomeActivityViewState>
        get() = _state

    init{
        val list = mutableListOf<Activity>()
        for (x in 1..5){
            list.add(
                Activity(
                activityId = x.toLong(),
                activityTitle = "Family Meeting",
                activityCategory = "Family",
                activityDate = Date(),
                    activityDesc = "Having a Family dinner at my palace"
            )
            )
        }
        viewModelScope.launch {
            _state.value = HomeActivityViewState(
                activity = list
            )
        }

    }
}

data class HomeActivityViewState(
    val activity: List<Activity> = emptyList()
)