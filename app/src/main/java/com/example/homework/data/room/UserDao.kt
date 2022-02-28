package com.example.homework.data.room

import androidx.room.*
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.homework.data.entity.User

@Dao
abstract class UserDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    abstract suspend fun insertUser(user: User)

    @Delete
    abstract suspend fun deleteUser(user: User)

    @Query(value = "SELECT * FROM Users WHERE user_Mail LIKE :mail")
    abstract fun findByMail(mail: String): List<User>

}