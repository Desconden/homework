package com.example.homework.data.repository

import com.example.homework.data.entity.User
import com.example.homework.data.room.UserDao

class UserRepository(
    private val userDao: UserDao
) {

    /*
    *   Add new user from register
    */
    //suspend fun addUser(user: User) = UserDao.insert(user)
}