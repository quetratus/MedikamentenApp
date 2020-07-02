package com.example.medikamentenapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.medikamentenapp.Repository.UserRepository
import com.example.medikamentenapp.db.UserDatabase
import java.lang.IllegalArgumentException

class UserViewModelFactory (private val repository: UserRepository, private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(repository, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
        }
}


