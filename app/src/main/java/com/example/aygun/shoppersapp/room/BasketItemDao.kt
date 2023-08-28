package com.example.aygun.shoppersapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.aygun.shoppersapp.data.entity.BasketItem
import com.example.aygun.shoppersapp.data.entity.HomeItem

@Dao
interface BasketItemDao {
    @Query("SELECT * FROM basket_item")
    suspend fun getAllBasketItems(): List<BasketItem>

    @Insert
    suspend fun addBasketItems(item: BasketItem)

    @Update
    suspend fun updateBasketItems(item: BasketItem)

    @Delete
    suspend fun deleteBasketItems(item: BasketItem)

}