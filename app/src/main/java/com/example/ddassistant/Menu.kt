package com.example.ddassistant

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
    }
    override fun onStart() {
        super.onStart()

        var profileName=intent.getStringExtra("name_extra")
        val userText =findViewById<TextView>(R.id.txt_activity_menu_user)
        userText.text= "Bienvenido" + "$profileName"
        //record
        val recordButton = findViewById<Button>(R.id.btn_activity_menu_record)
        recordButton.setOnClickListener {
            val intent = Intent(this, Record::class.java)
            startActivity(intent)
        }
        //take photo
        val takePhotoButton = findViewById<Button>(R.id.btn_activity_menu_take_photo)
        takePhotoButton.setOnClickListener {
            val intent2 = Intent(this,Calendar::class.java)
            startActivity(intent2)
        }
        //media
        val mediaButton = findViewById<Button>(R.id.btn_activity_menu_media)
        mediaButton.setOnClickListener {
            val intent3 = Intent(this,AudioPlayer::class.java)
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