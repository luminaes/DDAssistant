package com.example.ddassistant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.os.PersistableBundle
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.room.Dao
import com.example.ddassistant.data.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    private lateinit var userDao: UserDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userDao= AppDatabase.getDatabase(this).UserDao()
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        userDao= AppDatabase.getDatabase(this).UserDao()
        val userEditText=findViewById<EditText>(R.id.txt_activity_main_name)
        val passEditText=findViewById<EditText>(R.id.txt_activity_main_pass)
        val loginButton = findViewById<Button>(R.id.btn_activity_main_log)// cambiar login por login button
        var logInUserName = userEditText.text.toString()
        val logInUserPass = passEditText.text.toString()

        loginButton.setOnClickListener {
            userDao.findOne(logInUserName, logInUserPass).observe(this, Observer
            { checkUserName ->logInUserName=checkUserName.username})

            if(logInUserName !=null){
                val intent2 = Intent(this,Menu::class.java)
                startActivity(intent2)
            }
        }

        val reg = findViewById<Button>(R.id.btn_activity_main_reg)
        reg.setOnClickListener {
            val intent = Intent(this,SignUp::class.java)
            startActivity(intent)
        }

    }


    private fun checkUser(): UserDao {

        //reg.isEnabled=false
        lateinit var userDao: UserDao
        //var userDao = AppDatabase.getDatabase(application).UserDao()
        userDao=AppDatabase.getDatabase(this).UserDao()
        /*userDao.findOne(logInUserName,logInUserPass)?.observe(this) {
            reg.isEnabled=true
        }*/
        return userDao

    }

}