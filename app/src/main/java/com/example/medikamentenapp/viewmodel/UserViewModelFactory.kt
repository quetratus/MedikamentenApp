package com.example.medikamentenapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.medikamentenapp.db.UserDatabase
import java.lang.IllegalArgumentException

class UserViewModelFactory (
    private val dataSource: UserDatabase,
    private val application : Application) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
        }
    }


