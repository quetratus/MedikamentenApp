package com.example.medikamentenapp.notify


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.medikamentenapp.Repository.MedicamentRepository
import com.example.medikamentenapp.db.UserDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class ReminderBroadcast : BroadcastReceiver() {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onReceive(context: Context, intent: Intent) {
        uiScope.launch {

            val dao = UserDatabase.getDatabase(context)!!.daoAccess
            val repository = MedicamentRepository(dao)

            if (context != null && intent != null) {
                val med = intent.extras!!.getString("medName")?.let { repository.getMedByName(it) }
                if (med != null) {
                    NotificationHelper.createMedNotification(context, med, true)
                }
            }
        }
    }
}


