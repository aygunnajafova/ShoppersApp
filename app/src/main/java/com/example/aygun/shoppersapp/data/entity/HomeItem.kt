package com.example.aygun.shoppersapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "home_item")
data class HomeItem(@PrimaryKey(autoGenerate = true)
                    @ColumnInfo(name = "id") val id: Int,
                    @ColumnInfo(name = "name") val name: String,
                    @ColumnInfo(name = "price") val price: Int,
                    @ColumnInfo(name = "description") val description: String,
                    @ColumnInfo(name = "image") val image: String) : Serializable