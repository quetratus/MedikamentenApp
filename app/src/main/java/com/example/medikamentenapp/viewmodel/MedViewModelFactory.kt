package com.example.medikamentenapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.medikamentenapp.Repository.MedicamentRepository


class MedViewModelFactory(private val repository: MedicamentRepository) : ViewModelProvider.Factory {
    @SuppressWarnings("unchecked")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MedViewModel::class.java)) {
            return MedViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


