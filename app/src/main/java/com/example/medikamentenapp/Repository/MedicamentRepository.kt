package com.example.medikamentenapp.Repository

import com.example.medikamentenapp.dao.DaoAccess
import com.example.medikamentenapp.entities.Medicament


class MedicamentRepository(private val dao: DaoAccess) {

    val meds = dao.getAllMed()

    suspend fun insertMed(med: Medicament): Long {
        return dao.insertMed(med)
    }
}