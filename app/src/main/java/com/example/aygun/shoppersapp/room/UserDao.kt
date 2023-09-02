package com.example.aygun.shoppersapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.aygun.shoppersapp.data.entity.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    suspend fun getAllUsers() : List<User>

    @Insert
    suspend fun add(user: User)
}