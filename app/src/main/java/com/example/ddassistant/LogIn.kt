package com.example.ddassistant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.ddassistant.data.AppDatabase
import com.example.ddassistant.data.UserViewModel

class LogIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        val login = findViewById<Button>(R.id.loguearse)
        login.setOnClickListener {
            val userEditText = findViewById<EditText>(R.id.usuario)
            val passEditText = findViewById<EditText>(R.id.password)
            val logInUserName = userEditText.text.toString()
            val logInUserPass = passEditText.text.toString()
            //val userCheck = AppDatabase.getDatabase(application).UserDao()

            val userDao = AppDatabase.getDatabase(application).UserDao()
            if(userDao.findOne(logInUserName,logInUserPass)?.equals(null)!!){
                Toast.makeText(this, "Datos Incorrectos", Toast.LENGTH_LONG).show()

            }else{
                val intent2 = Intent(this,Menu::class.java)
                startActivity(intent2)
            }
        }


    }
}