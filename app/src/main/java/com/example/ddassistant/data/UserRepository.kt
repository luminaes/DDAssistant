package com.example.ddassistant.data

import androidx.lifecycle.LiveData


class UserRepository (private var userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAlldata()
     suspend fun addUser (user: User){
        userDao.addUser(user)
    }

}