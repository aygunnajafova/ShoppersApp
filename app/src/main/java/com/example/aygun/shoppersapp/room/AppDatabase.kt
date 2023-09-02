package com.example.aygun.shoppersapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.aygun.shoppersapp.data.entity.BasketItem
import com.example.aygun.shoppersapp.data.entity.HomeItem
import com.example.aygun.shoppersapp.data.entity.User

@Database(entities = [BasketItem::class,HomeItem::class,User::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getBasketItemDao() : BasketItemDao
    abstract fun getHomeItemDao() : HomeItemDao

    abstract fun getUserDao() : UserDao

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