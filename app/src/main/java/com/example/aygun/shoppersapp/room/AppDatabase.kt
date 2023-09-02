package com.example.aygun.shoppersapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.aygun.shoppersapp.data.entity.BasketItem
import com.example.aygun.shoppersapp.data.entity.HomeItem

@Database(entities = [BasketItem::class,HomeItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getBasketItemDao() : BasketItemDao
    abstract fun getHomeItemDao() : HomeItemDao

    companion object {
        var INSTANCE: AppDatabase? = null
        fun dataAccess(context: Context) : AppDatabase? {
            synchronized(AppDatabase::class) {
                INSTANCE = Room.databaseBuilder(context.applicationContext,
                    AppDatabase::class.java,"shoppers1.sqlite")
                    .createFromAsset("shoppers1.sqlite")
                    .build()
            }

            return INSTANCE
        }
    }
}