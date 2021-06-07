package com.example.ddassistant

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.ddassistant.data.User
import com.example.ddassistant.data.UserViewModel
import androidx.lifecycle.Observer
import com.example.ddassistant.data.AppDatabase
import com.example.ddassistant.data.UserDao

class SignUp : AppCompatActivity() {
    private lateinit var userDao: UserDao
    private lateinit var mUserViewModel:UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val cleanButton = findViewById<Button>(R.id.btn_activity_sing_in_clean)
        cleanButton.setOnClickListener {
            cleanFields()
        }

        val sing = findViewById<Button>(R.id.btn_activity_sing_in_register)
        sing.setOnClickListener {
            val userEditText = findViewById<EditText>(R.id.txt_activity_sing_in_user2)
            val userName = userEditText.text.toString()
            //userExist(userName)
            insertDataToDatabase()
        }

    }

    private fun insertDataToDatabase() {
        val userEditText = findViewById<EditText>(R.id.txt_activity_sing_in_user2)
        val passEditText = findViewById<EditText>(R.id.txt_activity_sing_in_pass_2)
        val emalEditText = findViewById<EditText>(R.id.txt_activity_sing_in_email2)
        val userName = userEditText.text.toString()
        val userPass = passEditText.text.toString()
        val userEmail = emalEditText.text.toString()

        if (imputCheck(userName, userPass, userEmail)){
            if(checkLength(userName, userPass)){
                if(checkEmail(userEmail)){
                    //crear objeto usuario
                    val user = User(0, userName, userPass, userEmail)
                    //añadir a la base
                    mUserViewModel.addUser(user)
                    Toast.makeText(this, "Registro exitoso", Toast.LENGTH_LONG).show()
                    cleanFields()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "Correo Invalido", Toast.LENGTH_LONG).show()
                }

            }else{
                Toast.makeText(this, "Usuario y contraseña deben tener un minimo de 6 caracteres", Toast.LENGTH_LONG).show()
            }
        }else{
            Toast.makeText(this, "Campos Incompletos", Toast.LENGTH_LONG).show()
        }

    }
    private fun imputCheck(userName: String, userPass: String, userEmail: String):Boolean{
        return !(TextUtils.isEmpty(userName)&&TextUtils.isEmpty(userPass) && TextUtils.isEmpty(userEmail))
    }

    private fun checkLength(userName: String, userPass: String):Boolean{
        return !(userName.length < 6 && userPass.length < 6)
    }

    private fun checkEmail(userEmail: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()
    }

    private fun userExist(userName: String) {
        userDao= AppDatabase.getDatabase(this).UserDao()
        userDao.findname(userName).observe(this, Observer
        { UserName ->callBackUser(UserName)})


    }

    private fun callBackUser(userName: User?){
        if(userName!=null){
            Toast.makeText(this, "Usuario ya Existente", Toast.LENGTH_SHORT).show()
        }else{
            insertDataToDatabase()
        }
    }

    private fun cleanFields(){
        val userEditText = findViewById<EditText>(R.id.txt_activity_sing_in_user2).text
        val passEditText = findViewById<EditText>(R.id.txt_activity_sing_in_pass_2).text
        val emalEditText = findViewById<EditText>(R.id.txt_activity_sing_in_email2).text
        var userName = userEditText
        var userPass = passEditText
        var userEmail = emalEditText
        userName.clear()
        userPass.clear()
        userEmail.clear()
    }


}