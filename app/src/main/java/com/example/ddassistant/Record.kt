package com.example.ddassistant

import android.Manifest
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.File
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
        var userNameExtra=intent.getStringExtra("userNameExtra")

        /*
        val extStorageDirectory = this.getExternalFilesDir(null) !!.absolutePath + "/audios"
        val audios = File(extStorageDirectory)
        if (!audios.exists())
            audios.mkdirs()

        val folder = File(filesDir.toString() + "/audios")
        if (!folder.exists()) {
            folder.mkdir()
        }*/



        val recordButton = findViewById<Button>(R.id.btn_activity_record_rec)
        val puaseResumeButton = findViewById<Button>(R.id.btn_activity_record_pause_resume)
        val stopButton = findViewById<Button>(R.id.btn_activity_record_stop)
        val microImage =findViewById<ImageView>(R.id.img_activity_record_mic)
        recordButton.setOnClickListener {
            startRecording()
            microImage.setImageResource(R.drawable.mocrofonorojotranspa)

        }
        puaseResumeButton.setOnClickListener {
            pauseRecording()
            microImage.setImageResource(R.drawable.mocrofonoamarillotranspa)
        }
        stopButton.setOnClickListener {
            stopRecording()
            microImage.setImageResource(R.drawable.mocrofonogristranspa)
        }

    }
    private fun pathOfAudio(date: String): String {
        var userNameExtra=intent.getStringExtra("userNameExtra")
        val folder= File(if (isExternalStorageWritable()) this.getExternalFilesDir(null)!!.absolutePath + "/audios/" +"$userNameExtra" else filesDir.toString() + "/audios/" +"$userNameExtra")
        if(!folder.exists()){
            folder.mkdirs()
        }
        return  folder.absolutePath +"/$date.mp3"
    }



    @RequiresApi(Build.VERSION_CODES.O)
    private fun setPermAndEmb(){ //permisos y establecer entorno de ejecucion
        if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            val permissions = arrayOf(android.Manifest.permission.RECORD_AUDIO, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE)
            ActivityCompat.requestPermissions(this, permissions,0)
           /* //val dateTime = LocalDateTime.now()
            //output = pathOfAudio(dateTime )*/
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
            val permissions = arrayOf(android.Manifest.permission.RECORD_AUDIO, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE)
            ActivityCompat.requestPermissions(this, permissions,0)
            var dateTime = LocalDateTime.now().toString()
            if (dateTime.contains(":")){
                dateTime = dateTime.replace(":", "")
            }
            output = pathOfAudio(dateTime)
            mediaRecorder = MediaRecorder()//creo instancia de media record
            mediaRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)// especifico fuente del audio
            mediaRecorder?.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)//fotmato
            mediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)//compresion
            mediaRecorder?.setOutputFile(output)//salida

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
            Toast.makeText(this, "Se finalizo la Grabacion", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "No hay Grabacion en curso", Toast.LENGTH_SHORT).show()
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

     private fun isExternalStorageWritable(): Boolean {
         val isSDPresent = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
         val isSDSupportedDevice = Environment.isExternalStorageRemovable()
         return isSDSupportedDevice && isSDPresent
    }

}