package com.example.ddassistant.data

import android.Manifest
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.ddassistant.R
import java.io.IOException
import java.time.LocalDateTime

class Record : AppCompatActivity() {
    private var output: String? = null
    private var mediaRecorder: MediaRecorder? = null
    private var state: Boolean = false
    private var recordingStopped: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStart() {
        super.onStart()
        val recordButton = findViewById<Button>(R.id.btn_activity_record_rec)
        val puaseResumeButton = findViewById<Button>(R.id.btn_activity_record_pause_resume)
        val stopButton = findViewById<Button>(R.id.btn_activity_record_stop)
        recordButton.setOnClickListener {
            startRecording()
        }
        puaseResumeButton.setOnClickListener {
            pauseRecording()
        }
        stopButton.setOnClickListener {
            stopRecording()
        }

    }
    private fun pathOfAudio(date: LocalDateTime): String {

        if(isExternalStorageWritable())
        {
            return this.getExternalFilesDir(null) !!.absolutePath + "/$date.aac"

        }
        else
        {
            // almacenamiento interno
            return  this.filesDir!!.absolutePath + "/$date.aac"
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setPermAndEmb(){ //permisos y establecer entorno de ejecucion
        if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            val permissions = arrayOf(android.Manifest.permission.RECORD_AUDIO, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE)
            ActivityCompat.requestPermissions(this, permissions,0)
            val dateTime = LocalDateTime.now()
            output = pathOfAudio(dateTime )
            mediaRecorder = MediaRecorder()//creo instancia de media record
            mediaRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)// especifico fuente del audio
            mediaRecorder?.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)//fotmato
            mediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)//compresion
            mediaRecorder?.setOutputFile(output)//salida
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun startRecording() {
        try {
            setPermAndEmb()
            mediaRecorder?.prepare()
            mediaRecorder?.start()
            state = true
            Toast.makeText(this, "Grabacion Iniciada!", Toast.LENGTH_SHORT).show()
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun stopRecording(){
        if(state){
            mediaRecorder?.stop()
            mediaRecorder?.release()
            state = false
        }else{
            Toast.makeText(this, "Se finalizo la Grabacion", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("RestrictedApi", "SetTextI18n")
    @TargetApi(Build.VERSION_CODES.N)
    private fun pauseRecording() {
        if(state) {
            if(!recordingStopped){
                Toast.makeText(this,"Grabacion Pausada!", Toast.LENGTH_SHORT).show()
                mediaRecorder?.pause()
                recordingStopped = true
                val buttonPauResu = findViewById<Button>(R.id.btn_activity_record_pause_resume)
                buttonPauResu.text = "Resumir"
            }else{
                resumeRecording()
            }
        }
    }

    @SuppressLint("RestrictedApi", "SetTextI18n")
    @TargetApi(Build.VERSION_CODES.N)
    private fun resumeRecording() {
        Toast.makeText(this,"Grabacion Resumida!", Toast.LENGTH_SHORT).show()
        mediaRecorder?.resume()
        val buttonPauResu = findViewById<Button>(R.id.btn_activity_record_pause_resume)
        buttonPauResu.text = "Pausar"
        recordingStopped = false
    }

     fun isExternalStorageWritable(): Boolean {
         val isSDPresent = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
         val isSDSupportedDevice = Environment.isExternalStorageRemovable()
         return isSDSupportedDevice && isSDPresent
    }

}