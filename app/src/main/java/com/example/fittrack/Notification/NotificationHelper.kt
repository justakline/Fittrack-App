package com.example.fittrack.Notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.fittrack.MainActivity

class NotificationHelper(private var co: Context) {
    private val CHANNEL_ID = "message id"
    private val NOTIFICATION_ID = 123
    val intent = Intent(this.co, MainActivity::class.java)


    fun Notification(){
        createNotificationHelper()
        val senInt = Intent(co,MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this.co, 123, intent, PendingIntent.FLAG_IMMUTABLE)

        val builder = NotificationCompat.Builder(this.co, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_media_play)
            .setContentTitle("Workout Started")
            .setContentText("Lets get after it!")
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

    }

    fun createNotificationHelper(){
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            val name = CHANNEL_ID
            val descrip = "Channel descrip"
            val imports = NotificationManager.IMPORTANCE_DEFAULT
            val channels = NotificationChannel(CHANNEL_ID, name, imports).apply {
                description = descrip
            }
            val notificationManager = co.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channels)
        }
    }

}