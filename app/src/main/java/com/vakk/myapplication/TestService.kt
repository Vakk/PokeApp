package com.vakk.myapplication

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import com.vakk.common.cast

class TestService : Service() {

    companion object {
        private const val CHANNEL_ID = "TimerTest"
    }

    private val currentTime: Long
        get() = System.currentTimeMillis()

    private var startTime = currentTime

    private lateinit var notificationManager: NotificationManager

    override fun onCreate() {
        super.onCreate()
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE)!!.cast()
        startForeground(25, prepareNotification())
    }

    private fun prepareNotification(): Notification {
        initChannel()
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification.Builder(this, CHANNEL_ID)
        } else {
            Notification.Builder(this)
        }.let {
            it.setSmallIcon(R.mipmap.ic_launcher_round)
            it.setContentTitle("Timer title.")
            it.setSubText("Sub text")
            it.setContentText("Timer was started.")
            it.build()
        }.apply {
            notificationManager.notify(25, this)
        }
    }

    private fun initChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel
            val name = "Timer channel"
            val descriptionText = "This is the description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel(CHANNEL_ID, name, importance)
            mChannel.description = descriptionText
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onBind(intent: Intent?) = object : CustomTimerAidlService.Stub() {
        override fun getTotalDuration(): Long {
            return currentTime - startTime
        }

        override fun restart() {
            startTime = currentTime
        }
    }
}