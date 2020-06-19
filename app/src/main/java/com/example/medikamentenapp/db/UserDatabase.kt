package com.example.medikamentenapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.*
import androidx.room.RoomDatabase
import com.example.medikamentenapp.dao.DaoAccess
import com.example.medikamentenapp.entities.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract val daoAccess: DaoAccess

    companion object {

        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase? {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "user_table"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance

                }

                return instance
            }


        }
    }
}