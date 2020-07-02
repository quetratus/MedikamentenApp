package com.example.medikamentenapp.Repository

import com.example.medikamentenapp.dao.DaoAccess
import com.example.medikamentenapp.entities.User

class UserRepository(private val dao: DaoAccess) {

   // val users = dao.getAllUsers()

    suspend fun insertUser(user: User): Long {
        return dao.insertUser(user)
    }

    fun getUser (name: String, password: String): User {
        val user = dao.getUser(name, password)
        return user
    }

}





