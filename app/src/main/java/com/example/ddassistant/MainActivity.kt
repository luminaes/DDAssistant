package com.example.ddassistant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.widget.EditText
import android.widget.Toast
import androidx.room.Dao
import com.example.ddassistant.data.AppDatabase
import com.example.ddassistant.data.User
import com.example.ddassistant.data.UserDao
import com.example.ddassistant.data.UserRepository

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val login = findViewById<Button>(R.id.btn_activity_main_log)
        login.setOnClickListener {
            val userEditText=findViewById<EditText>(R.id.txt_activity_main_name)
            val passEditText=findViewById<EditText>(R.id.txt_activity_main_pass)
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




            //if (userCheck.findOne()){            }
        }

        val reg = findViewById<Button>(R.id.btn_activity_main_reg)
        reg.setOnClickListener {
            val intent = Intent(this,SignUp::class.java)
            startActivity(intent)
        }

    }
}