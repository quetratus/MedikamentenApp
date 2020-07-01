package com.example.medikamentenapp.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="user_table")
data class User (
    @PrimaryKey
    @ColumnInfo(name ="user_name")
    var name: String,

    @ColumnInfo(name="user_password")
    var password: String
)

@Entity(tableName = "medicament_table")
data class Medicament(
    @PrimaryKey(autoGenerate = true)
    var medID: Long,

    @ColumnInfo(name = "med_name")
    var med_name: String,

    @ColumnInfo(name = "med_username")
     var med_username: String,

    @ColumnInfo(name = "dosis")
    var med_dosis: String,

    @ColumnInfo(name = "time_1")
    var med_time1: String,

    @ColumnInfo(name = "time_2")
    var med_time2: String,

    @ColumnInfo(name = "time_3")
    var med_time3: String
)


