package com.example.medikamentenapp.Repository

import com.example.medikamentenapp.dao.DaoAccess
import com.example.medikamentenapp.entities.Medicament


class MedicamentRepository(private val dao: DaoAccess) {

    val meds = dao.getAllMed()

    suspend fun insertMed(meds: Medicament): String {
        return dao.insertMed(meds)
    }
}