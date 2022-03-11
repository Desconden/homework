package com.example.homework.data.room

import androidx.room.*
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.homework.data.entity.Activity
import com.example.homework.data.entity.User

@Dao
abstract class UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertUser(user: User)

    @Delete
    abstract suspend fun deleteUser(user: User)

    @Update
    abstract suspend fun updateUser(user: User)

    @Query ("SELECT * FROM Users WHERE user_id = :userID")
    abstract suspend fun getUserById(userID: Long): User

    @Query(value = "SELECT * FROM Users WHERE user_Mail = :mail")
    abstract suspend fun findByMail(mail: String): User?

}