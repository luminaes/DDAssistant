package com.example.ddassistant

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.File
import java.lang.Exception
import java.util.Collections.max

class AudioPlayer : AppCompatActivity() {

    private var mp: MediaPlayer? =null
    private var currentSong = mutableListOf(R.raw.push)
   // private var currentSong = mutableListOf("/data/user/0/com.example.ddassistant/files/audios/push.mp3")
    private lateinit var seekBar: SeekBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audio_player)
        //controlSound(currentSong[0])

    }

    override fun onStart() {
        super.onStart()
        val songsList=audioListString()
        val position = 0
        val lonOfList = audiosQuantity()
        val playButton  =findViewById<FloatingActionButton>(R.id.flo_btn_activity_audio_player_play)
        val pauseButton  =findViewById<FloatingActionButton>(R.id.flo_btn_activity_audio_player_pause)
        val stopButton  =findViewById<FloatingActionButton>(R.id.flo_btn_activity_audio_player_stop)
        val nextButton = findViewById<FloatingActionButton>(R.id.flo_btn_activity_audio_player_next)
        val prevButton = findViewById<FloatingActionButton>(R.id.flo_btn_activity_audio_player_prev)
        playButton.scaleType = ImageView.ScaleType.CENTER
        pauseButton.scaleType = ImageView.ScaleType.CENTER
        stopButton.scaleType = ImageView.ScaleType.CENTER
        nextButton.scaleType = ImageView.ScaleType.CENTER
        prevButton.scaleType = ImageView.ScaleType.CENTER
        var userNameExtra=intent.getStringExtra("userNameExtra")
        val seekbar = findViewById<SeekBar>(R.id.ske_activity_audio_player)
        val id = currentSong[0]
        playButton.setOnClickListener{
            if (mp== null ){
                mp= MediaPlayer.create(this, Uri.parse(songsList[0]))
                // mp= MediaPlayer.create(this, Uri.parse(song.toString()) )
                Log.d("AudioPlayer","ID: ${mp!!.audioSessionId} seconds")
                //initialiseSeekBar()
            }
            mp?.start()
            Log.d("AudioPlayer","Duration: ${mp!!.duration/1000} seconds")
        }

        pauseButton.setOnClickListener{
            if(mp !==null){
                mp?.pause()
                Log.d("AudioPlayer","Pause at: ${mp!!.currentPosition/1000} seconds")
            }else Toast.makeText(this, "No Se Esta Reproduciendo Audio", Toast.LENGTH_SHORT).show()
        }
        stopButton.setOnClickListener{
            if(mp !==null){
                mp?.stop()
                mp?.reset()
                mp?.release()
                mp = null
            }
        }

        nextButton.setOnClickListener {

        }
    }

    private fun controlSound (id:Int){
        val playButton = findViewById<FloatingActionButton>(R.id.flo_btn_activity_audio_player_play)
        val pauseButton = findViewById<FloatingActionButton>(R.id.flo_btn_activity_audio_player_play)
        val stopButton = findViewById<FloatingActionButton>(R.id.flo_btn_activity_audio_player_stop)
        val seekbar = findViewById<SeekBar>(R.id.ske_activity_audio_player)
        playButton.setOnClickListener{
            if (mp== null ){
                mp= MediaPlayer.create(this,id)
                Log.d("AudioPlayer","ID: ${mp!!.audioSessionId} seconds")
                //initialiseSeekBar()
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



   /*     seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) mp?.seekTo(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })*/



    }
    private fun initialiseSeekBar(){
        val seekbar = findViewById<SeekBar>(R.id.ske_activity_audio_player)
        seekbar.max = mp!!.duration
        //val handler = Handler()
        val handler = Handler(Looper.getMainLooper())
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

    private fun audioListString(): Array<String> {
        var userNameExtra=intent.getStringExtra("userNameExtra")
        val folder= File(if (isExternalStorageWritable()) this.getExternalFilesDir(null)!!.absolutePath + "/audios/" +"$userNameExtra/" else filesDir.toString() + "/audios/" +"$userNameExtra/")
        audiosQuantity()
        var i:Int =0
        val songs = Array<String>(audiosQuantity()){ "it = $it" } // boxing int for kotlin
        File("$folder").walk().forEach {
            if(it!=folder){
                songs[i] = it.toString()
                i++
            }
        }
        return songs
    }

    private fun audiosQuantity():Int{
        var userNameExtra=intent.getStringExtra("userNameExtra")
        val folder= File(if (isExternalStorageWritable()) this.getExternalFilesDir(null)!!.absolutePath + "/audios/" +"$userNameExtra/" else filesDir.toString() + "/audios/" +"$userNameExtra/")
        var j:Int =0
        File("$folder").walk().forEach {
            j++
        }
        return j-1
    }


    private fun isExternalStorageWritable(): Boolean {
        val isSDPresent = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
        val isSDSupportedDevice = Environment.isExternalStorageRemovable()
        return isSDSupportedDevice && isSDPresent
    }


}