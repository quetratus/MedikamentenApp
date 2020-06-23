package com.example.medikamentenapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.medikamentenapp.Repository.UserRepository
import com.example.medikamentenapp.db.UserDatabase
import java.lang.IllegalArgumentException

class UserViewModelFactory (private val repository: UserRepository) : ViewModelProvider.Factory {
    @SuppressWarnings("unchecked")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
        }
}


