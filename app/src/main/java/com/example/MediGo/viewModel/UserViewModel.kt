package com.example.MediGo.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.MediGo.model.AppDatabase
import com.example.MediGo.model.Local.User
import com.example.MediGo.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: UserRepository

    init {
        val userDao = AppDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
    }

    fun loginUser(username: String, password: String): LiveData<User?> {
        return repository.loginUser(username, password)
    }

    fun signupUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.signupUser(user)
        }
    }
}
