package com.example.medikamentenapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.medikamentenapp.dao.DaoAccess
import com.example.medikamentenapp.entities.Medicament
import com.example.medikamentenapp.entities.User

@Database(entities = [User::class, Medicament::class], version = 11, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract val daoAccess : DaoAccess

    //singelton pattern
    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null
        fun getDatabase(context: Context): UserDatabase? {
            synchronized(this) {
                // Copy the current value of INSTANCE to a local variable so Kotlin can smart cast.
                // Smart cast is only available to local variables.
                var instance = INSTANCE
                // If instance is `null` make a new database instance.
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "USER DATABASE"
                    )
                        // Wipes and rebuilds instead of migrating if no Migration object
                        .fallbackToDestructiveMigration()
                        .build()
                    // Assign INSTANCE to the newly created database.
                }
                // Return instance; smart cast to be non-null.
                return instance
            }
        }
    }
}