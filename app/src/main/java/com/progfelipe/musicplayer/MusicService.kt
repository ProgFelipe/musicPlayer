package com.progfelipe.musicplayer

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder

class MusicService : Service() {

    //region Bind
    private val myBinder = MyLocalBinder()

    inner class MyLocalBinder : Binder() {
        fun getService(): MusicService {
            return this@MusicService
        }
    }

    override fun onBind(intent: Intent): IBinder {
        return myBinder
    }
    //endregion


    private var mediaPlayer: MediaPlayer? = null
    //private val messenger: Messenger(IncomingHanlder())


    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer.create(this, R.raw.the_pirate_and_the_dancer)
        mediaPlayer?.isLooping = true
        mediaPlayer?.setVolume(80F, 80F)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mediaPlayer?.start()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.stop()
        mediaPlayer?.release()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mediaPlayer?.stop()
        mediaPlayer?.release()
    }


    fun starMusic() {
        if (mediaPlayer?.isPlaying == false)
            mediaPlayer?.start()
    }

    fun stopMusic() {
        mediaPlayer?.stop()
    }

    fun pauseMusic() {
        mediaPlayer?.pause()
    }

    fun getTrackName(): String{
        return R.raw.the_pirate_and_the_dancer.toString()
    }
}
