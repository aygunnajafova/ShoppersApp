package com.example.aygun.shoppersapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "user")
data class User(@PrimaryKey(autoGenerate = true)
                @ColumnInfo(name = "id") val id: Int,
                @ColumnInfo(name = "username") val username: String,
                @ColumnInfo(name = "email") val email: String,
                @ColumnInfo(name = "password") val password: String) : Serializable {
}