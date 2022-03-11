package com.example.homework.data.reprository

import com.example.homework.data.entity.User
import com.example.homework.data.room.UserDao

class UserRepository(
    private val userDao: UserDao
) {
    suspend fun addUser(user: User) = userDao.insertUser(user)
    suspend fun deleteUser(user: User) = userDao.deleteUser(user)
    suspend fun updateUser(user: User) = userDao.updateUser(user)
    suspend fun getUserById(userID: Long): User = userDao.getUserById(userID)
    suspend fun findByMail(mail: String): User? = userDao.findByMail(mail)
}