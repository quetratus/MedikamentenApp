package com.example.medikamentenapp.Repository

import androidx.lifecycle.LiveData
import com.example.medikamentenapp.dao.DaoAccess
import com.example.medikamentenapp.entities.Medicament


class MedicamentRepository(private val dao: DaoAccess) {

    suspend fun insertMed(med: Medicament): Long {
        return dao.insertMed(med)
    }

    fun getAllMed(username: String): LiveData<List<Medicament>> {
        return dao.getAllMed(username)
    }

    suspend fun getMedByName(name: String) : Medicament {
        return dao.getMedByName(name)
    }
}