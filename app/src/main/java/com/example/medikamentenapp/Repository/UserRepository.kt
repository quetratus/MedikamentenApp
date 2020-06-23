package com.example.medikamentenapp.Repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.medikamentenapp.dao.DaoAccess
import com.example.medikamentenapp.db.UserDatabase
import com.example.medikamentenapp.entities.User


class UserRepository(private val dao: DaoAccess) {

    val users = dao.getAllUsers()

    suspend fun insert(user: User): Long {
        return dao.insertUser(user)
    }
}

  /*
    private var daoAccess: DaoAccess? = null
    private var allData: LiveData<List<User>>? = null

    init {
        //fetching user database
        val db = UserDatabase.getDatabase(application)
        daoAccess = db?.daoAccess()
        allData = daoAccess?.getDetails()

    }

    fun getAllData(): LiveData<List<User>>? {
        return allData
    }

    fun insertData(data: User) {
        daoAccess?.let { LoginInsertion(it).execute(data) }
    }

    private class LoginInsertion(private val daoAccess: DaoAccess) :
        AsyncTask<User, Void, Void>() {

        override fun doInBackground(vararg data: User): Void? {

            daoAccess.insertUserData(data[0])
            return null

        }

    }


}
*/