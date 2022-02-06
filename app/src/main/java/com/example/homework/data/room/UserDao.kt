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
    abstract suspend fun insert(entity: User): Long

    @Query(value = "SELECT userPassword FROM User WHERE userMail = :mail")
    abstract suspend fun getPasswordWithEmail(mail: String): User


}