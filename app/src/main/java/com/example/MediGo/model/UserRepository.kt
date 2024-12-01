package com.example.MediGo.repository

import androidx.lifecycle.LiveData
import com.example.MediGo.model.Local.User
import com.example.MediGo.model.Local.UserDao

class UserRepository(private val userDao: UserDao) {

    fun loginUser(username: String, password: String): LiveData<User?> {
        return userDao.getUser(username, password)
    }

    suspend fun signupUser(user: User) {
        userDao.insertUser(user)
    }
}
