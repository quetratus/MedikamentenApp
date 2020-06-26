package com.example.medikamentenapp.viewmodel

class MedViewModelFactory
/*
class MedViewModelFactory (private val repository: MedicamentRepository) : ViewModelProvider.Factory {
    @SuppressWarnings("unchecked")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MedViewModel::class.java)) {
            return MedViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

 */