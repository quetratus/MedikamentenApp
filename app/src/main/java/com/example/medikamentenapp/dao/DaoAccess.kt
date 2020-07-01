package com.example.medikamentenapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import com.example.medikamentenapp.entities.Medicament
import com.example.medikamentenapp.entities.User

@Dao
interface DaoAccess {

    @Insert(onConflict = IGNORE)
    suspend fun insertUser(user: User) : Long //   query is written  for insert all details of user

    @Query("SELECT * FROM user_table WHERE  user_name LIKE :username AND user_password LIKE :password")
    fun getUser(username: String, password: String): User

    @Query("select * from user_table")
    fun getAllUsers(): LiveData<List<User>> //   query is written for fetching all details of user

    @Insert(onConflict = REPLACE)
    suspend fun insertMed(med: Medicament) : Long

    @Transaction
    @Query("SELECT * FROM medicament_table WHERE med_username =:username")
    suspend fun getAllMed(username:String): LiveData<List<Medicament>>

}
