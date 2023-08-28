package com.example.aygun.shoppersapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity(tableName = "basket_item")
data class BasketItem(@PrimaryKey(autoGenerate = true)
                      @ColumnInfo(name = "id") val id: Int,
                      @ColumnInfo(name = "name") val name: String,
                      @ColumnInfo(name = "quantity") var quantity: Int,
                      @ColumnInfo(name = "price") var price: Int,
                      @ColumnInfo(name = "image") val image: String) : Serializable
