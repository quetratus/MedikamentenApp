package com.example.medikamentenapp.Repository

import com.example.medikamentenapp.dao.DaoAccess
import com.example.medikamentenapp.entities.User


class UserRepository(private val dao: DaoAccess) {

    val users = dao.getAllUsers()

    suspend fun insert(user: User): Long {
        return dao.insertUser(user)
    }
}

