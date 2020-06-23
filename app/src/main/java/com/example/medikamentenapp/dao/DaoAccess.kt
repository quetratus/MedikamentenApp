package com.example.medikamentenapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.medikamentenapp.entities.User

@Dao
interface DaoAccess {

    @Insert
    suspend fun insertUser(user: User) : Long //   query is written  for insert all details of user

    /*@Update
    suspend fun updateUser(user:User) : Int */

    @Query("select * from user_table")
    fun getAllUsers(): LiveData<List<User>> //   query is written for fetching all details of user

    /*@Query("DELETE FROM user_table WHERE user_id = :id")
    suspend fun deleteByUserId(id: Long) :Int */

}