package com.example.musicapp


import android.content.Intent
import android.app.Service
import android.media.MediaPlayer
import android.os.IBinder
import android.provider.Settings

abstract class NewService : Service() {
    private lateinit var player:MediaPlayer  //creating a variable for mediaplayer
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int { //onStartCommand method
        player =
            MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI) // Uri of the audio file
        player.isLooping = true // Set looping
        player.start()  //start the player
        return START_STICKY //super.onStartCommand(intent, flags, startId)
    }
    override fun onDestroy() { //onDestroy method
        super.onDestroy()   //super.onDestroy()
        player.stop() //stop the player
    }
    override fun onBind(intent: Intent?): IBinder? { //this line means that we are not going to bind our service with any activity. and IBinder is an interface, its function is to bind the service with activity.
        return null
    }
}