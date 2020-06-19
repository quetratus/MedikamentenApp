package com.example.medikamentenapp.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName ="user_table")
data class User (
    //declaration of user table columns
    @PrimaryKey(autoGenerate = true)
    var userId: Long = 0L,

    @ColumnInfo(name ="user")
    var name: String? = null,

    @ColumnInfo(name="password")
    var password: String? = null

)
