package com.example.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> = _user

    fun setUser(newUser: User) {
        _user.value = newUser
    }

    fun clearUser() {
        _user.value = null
    }
}
