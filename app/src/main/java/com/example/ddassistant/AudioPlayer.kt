package com.example.ddassistant

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AudioPlayer : AppCompatActivity() {
    private var mp: MediaPlayer? =null
    private var currentSong = mutableListOf(R.raw.a)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audio_player)

        val playButton  =findViewById<FloatingActionButton>(R.id.flo_btn_activity_audio_player_play)
        val pauseButton  =findViewById<FloatingActionButton>(R.id.flo_btn_activity_audio_player_play)
        val stopButton  =findViewById<FloatingActionButton>(R.id.flo_btn_activity_audio_player_play)
        playButton.scaleType = ImageView.ScaleType.CENTER
        pauseButton.scaleType = ImageView.ScaleType.CENTER
        stopButton.setScaleType(ImageView.ScaleType.CENTER)
        controlSound(currentSong[0])
    }
    private fun controlSound (id:Int){
        val playButton  =findViewById<FloatingActionButton>(R.id.flo_btn_activity_audio_player_play)
        val pauseButton  =findViewById<FloatingActionButton>(R.id.flo_btn_activity_audio_player_play)
        val stopButton  =findViewById<FloatingActionButton>(R.id.flo_btn_activity_audio_player_play)

        playButton.setOnClickListener{
            if (mp== null ){
                mp= MediaPlayer.create(this,id)
                Log.d("AudioPlayer","ID: ${mp!!.duration/1000} seconds")
                //initialiseSeekBar()
            }
            mp?.start()
            Log.d("AudioPlayer","Duration: ${mp!!.duration/1000} seconds")
        }



    }

}