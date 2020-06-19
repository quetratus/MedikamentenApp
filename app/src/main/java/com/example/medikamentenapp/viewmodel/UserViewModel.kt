package com.example.medikamentenapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.medikamentenapp.Repository.UserDetailsRepository
import com.example.medikamentenapp.db.UserDatabase
import com.example.medikamentenapp.entities.User

class UserViewModel(
    val database: UserDatabase,
    application: Application) : AndroidViewModel(application) {}




/*
{

    private var repository: UserDetailsRepository
    private var getAllData: LiveData<List<User>>

    init {

        repository = UserDetailsRepository(application)
        getAllData = repository.getAllData()!!
    }

    fun insert(data: User) {
        repository.insertData(data)
    }

    fun getGetAllData(): LiveData<List<User>> {
        return getAllData
    }
}


 */