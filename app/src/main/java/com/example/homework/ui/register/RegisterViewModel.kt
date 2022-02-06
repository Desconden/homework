package com.example.homework.ui.register

import androidx.lifecycle.ViewModel
import com.example.homework.data.entity.User
import com.example.homework.data.repository.UserRepository
import kotlinx.coroutines.flow.StateFlow

class RegisterViewModel(
    private val userRepository: UserRepository
): ViewModel()
/*
suspend fun saveUser(user: User): Long{
    return UserRepository.addUser(user)
}
*/