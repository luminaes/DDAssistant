package com.example.ddassistant

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.ImageView
import android.widget.SeekBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.lang.Exception
import java.util.Collections.max

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
        val seekbar = findViewById<SeekBar>(R.id.ske_activity_audio_player)
        playButton.setOnClickListener{
            if (mp== null ){
                mp= MediaPlayer.create(this,id)
                Log.d("AudioPlayer","ID: ${mp!!.audioSessionId} seconds")
                initialiseSeekBar()
            }
            mp?.start()
            Log.d("AudioPlayer","Duration: ${mp!!.duration/1000} seconds")
        }

        pauseButton.setOnClickListener{
            if(mp !==null) mp?.pause()
            Log.d("AudioPlayer","Pause at: ${mp!!.currentPosition/1000} seconds")

        }
        stopButton.setOnClickListener{
            if(mp !==null){
                mp?.stop()
                mp?.reset()
                mp?.release()
                mp = null
            }
        }
        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) mp?.seekTo(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })


    }
    private fun initialiseSeekBar(){
        val seekbar = findViewById<SeekBar>(R.id.ske_activity_audio_player)
        seekbar.max = mp!!.duration
        val handler = Handler()
        handler.postDelayed(object:Runnable{
            override fun run() {
                try {
                    seekbar.progress=mp!!.currentPosition
                    handler.postDelayed(this,1000)
                }catch (e:Exception){
                    seekbar.progress = 0
                }
            }
        },0 )
    }


}