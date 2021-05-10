package com.example.ddassistant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.ddassistant.data.Record

class Menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)


    }
    override fun onStart() {
        super.onStart()
        //record
        val recordButton = findViewById<Button>(R.id.btn_activity_menu_record)
        recordButton.setOnClickListener {
            val intent = Intent(this,Record::class.java)
            startActivity(intent)
        }
        //take photo
        val takePhotoButton = findViewById<Button>(R.id.btn_activity_menu_take_photo)
        takePhotoButton.setOnClickListener {
            val intent2 = Intent(this,TakePhoto::class.java)
            startActivity(intent2)
        }
        //media
        val mediaButton = findViewById<Button>(R.id.btn_activity_menu_media)
        mediaButton.setOnClickListener {
            val intent3 = Intent(this,Media::class.java)
            startActivity(intent3)
        }
        //send location
        val locationButton = findViewById<Button>(R.id.btn_activity_menu_send_location)
        locationButton.setOnClickListener {
            val intent4 = Intent(this,SendLocation::class.java)
            startActivity(intent4)
        }

    }

}