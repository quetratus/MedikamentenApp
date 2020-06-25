package com.example.medikamentenapp.entities

import androidx.room.*
import java.sql.Time

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

@Entity(tableName = "medicament_table")
data class Medicament(
    @PrimaryKey(autoGenerate = true)
    var medID: Int,

    @ColumnInfo(name = "med_name")
    var med_name: String,

   /* @ColumnInfo(name = "user")
    var med_userID: Int, */

    @ColumnInfo(name = "dosis")
    var med_dosis: String,

    @ColumnInfo(name = "time_1")
    var med_time1: Time,

    @ColumnInfo(name = "time_2")
    var med_time2: Time,

    @ColumnInfo(name = "time_3")
    var med_time3: Time
)

data class UserWithMed(
    @Embedded val user: User,
    @Relation(
        parentColumn = "userID",
        entityColumn = "med_userID"
    )
    val medication: List<Medicament>
)

