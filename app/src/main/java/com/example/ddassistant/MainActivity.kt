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
    private lateinit var userEditText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userDao= AppDatabase.getDatabase(this).UserDao()
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        userDao= AppDatabase.getDatabase(this).UserDao()
        userDao.readAlldata().observe(this, Observer { usuarios -> usuarios.forEach { println(it) } })
        userEditText = findViewById<EditText>(R.id.txt_activity_main_name)
        val passEditText=findViewById<EditText>(R.id.txt_activity_main_pass)
        val loginButton = findViewById<Button>(R.id.btn_activity_main_log)// cambiar login por login button


        loginButton.setOnClickListener {
            var logInUserName = userEditText.text.toString()
            val logInUserPass = passEditText.text.toString()
            userDao.findOne(logInUserName, logInUserPass).observe(this, Observer
            { UserName ->callBackLogin(UserName)})


        }

        val reg = findViewById<Button>(R.id.btn_activity_main_reg)
        reg.setOnClickListener {
            val intent = Intent(this,SignUp::class.java)
            startActivity(intent)
        }

    }

    private fun callBackLogin(userName: User?) {
        if(userName !=null){
            val intent2 = Intent(this,Menu::class.java)
            startActivity(intent2)
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