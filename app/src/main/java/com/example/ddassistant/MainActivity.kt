package com.example.ddassistant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.os.Message
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
    private lateinit var passEditText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userDao= AppDatabase.getDatabase(this).UserDao()
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        userDao= AppDatabase.getDatabase(this).UserDao()
        //data de todos los usuarios por consola
        userDao.readAlldata().observe(this, Observer {usuarios -> usuarios.forEach{println(it) }})
        userEditText = findViewById<EditText>(R.id.txt_activity_main_name)
        passEditText = findViewById<EditText>(R.id.txt_activity_main_pass)
        val loginButton = findViewById<Button>(R.id.btn_activity_main_log)

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
            Toast.makeText(this, "LogIn Exitoso", Toast.LENGTH_SHORT).show()
            val intent2 = Intent(this,Menu::class.java)
            //var logInUserName = userEditText.text.toString()
            //intent2.putExtra("name_extra",logInUserName)
            intent2.putExtra("userNameExtra",userName.username )
            startActivity(intent2)
            cleanEdittext()
        }else Toast.makeText(this, "Datos Incorrectos!", Toast.LENGTH_SHORT).show()

    }

    private fun cleanEdittext(){
        userEditText = findViewById<EditText>(R.id.txt_activity_main_name)
        passEditText = findViewById<EditText>(R.id.txt_activity_main_pass)
        var logInUserName = userEditText.setText("")
        val logInUserPass = passEditText.setText("")
    }

}