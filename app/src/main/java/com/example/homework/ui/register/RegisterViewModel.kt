package com.example.homework.ui.register

import androidx.lifecycle.ViewModel
import com.example.homework.Graph
import com.example.homework.data.entity.Activity
import com.example.homework.data.entity.User
import com.example.homework.data.reprository.UserRepository

class RegisterViewModel(
    private val userRepository: UserRepository = Graph.userRepository
): ViewModel() {
    suspend fun registerUser(user: User) {
        return userRepository.addUser(user)
    }

}

