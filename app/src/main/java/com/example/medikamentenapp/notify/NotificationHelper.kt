package com.example.medikamentenapp.notify

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.medikamentenapp.MainActivity
import com.example.medikamentenapp.R
import com.example.medikamentenapp.entities.Medicament

object NotificationHelper {

    fun createNotificationChannel(context: Context, importance: Int, name: String, description: String){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Created a unique name for the notification channel. The name and description are displayed in the application’s Notification settings.
            val channelId = "${context.packageName}-$name"
            val channel = NotificationChannel(channelId, name, importance)
            channel.description = description

            // Created the channel using the NotificationManager.
            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun createMedNotification(context: Context, med: Medicament, autoCancel: Boolean) {
        // Create the unique channelId for this app using the package name and app name.
        val channelId = "${context.packageName}-${context.getString(R.string.app_name)}"
        // Use NotificationCompat.Builder to begin building the notification.
        val notificationBuilder = NotificationCompat.Builder(context, channelId).apply {
            //setSmallIcon(R.drawable.blume)
            setContentTitle(med.med_name)
            setContentText("Einnahme ${med.med_dosis} von ${med.med_name} nicht vergessen!")
            priority = NotificationCompat.PRIORITY_DEFAULT
            setAutoCancel(autoCancel) // Set the notification to auto cancel when tapped.
            // Created an Intent to launch the MainActivity.
            val intent = Intent(context, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            // Wrapped the Intent in a PendingIntent, created through the getActivity() method which returns a description of an Activity to be launched.
            val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
            // Called setContentIntent() to attach it to the NotificationCompat.Builder.
            setContentIntent(pendingIntent)
        }

        // Used the app’s Context to get a reference to NotificationManagerCompat.
        val notificationManager = NotificationManagerCompat.from(context)
        // Called notify() on the NotificationManager passing in an identifier and the notification.
        notificationManager.notify(med.medID.toInt(), notificationBuilder.build())
    }

}