package com.example.medikamentenapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.medikamentenapp.notify.NotificationHelper
import com.example.medikamentenapp.viewmodel.LoggedInUserView


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var username: LoggedInUserView
        NotificationHelper.createNotificationChannel(this, 3, getString(R.string.app_name), "VergissMeinNicht notification channel")
        setContentView(R.layout.activity_main)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return navController.navigateUp()
    }


}