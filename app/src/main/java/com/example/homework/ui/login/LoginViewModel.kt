package com.example.homework.ui.login

import androidx.lifecycle.ViewModel
import com.example.homework.Graph
import com.example.homework.data.entity.User
import com.example.homework.data.reprository.UserRepository


class LoginViewModel(
    private val userRepository: UserRepository = Graph.userRepository
):ViewModel() {
    suspend fun findUserByMail(mail: String): User? {
        return userRepository.findByMail(mail)
    }
}
