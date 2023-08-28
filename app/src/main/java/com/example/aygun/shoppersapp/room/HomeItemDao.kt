package com.example.aygun.shoppersapp.room

import androidx.room.Dao
import androidx.room.Query
import com.example.aygun.shoppersapp.data.entity.HomeItem

@Dao
interface HomeItemDao {
    @Query("SELECT * FROM home_item")
    suspend fun getAllHomeItems(): List<HomeItem>

}