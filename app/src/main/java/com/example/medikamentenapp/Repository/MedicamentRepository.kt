package com.example.medikamentenapp.Repository

import androidx.lifecycle.LiveData
import com.example.medikamentenapp.dao.DaoAccess
import com.example.medikamentenapp.entities.Medicament


class MedicamentRepository(private val dao: DaoAccess) {

 //val meds = dao.getAllMed()

    suspend fun insertMed(med: Medicament): Long {
        return dao.insertMed(med)
    }

    suspend fun getAllMed(username: String): LiveData<List<Medicament>> {
        return dao.getAllMed(username)
    }


}