package com.example.medikamentenapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.medikamentenapp.entities.User

@Dao
interface DaoAccess {

    @Insert
    fun insertUserData(user: User)  //   query is written  for insert all details of user

    @Query("select * from user_table")
    fun getDetails(): LiveData<List<User>> //   query is written for fetching all details of user

    @Query("DELETE FROM user_table WHERE userId = :id")
    fun deleteByUserId(id: Long)
}