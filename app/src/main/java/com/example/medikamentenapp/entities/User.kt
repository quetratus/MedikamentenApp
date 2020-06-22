package com.example.medikamentenapp.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="user_table")
data class User (
    //declaration of user table columns
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="user_id")
    var userId: Int,

    @ColumnInfo(name ="user_name")
    var name: String,

    @ColumnInfo(name="user_password")
    var password: String

)
