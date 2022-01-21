package com.example.testkmm.android

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testkmm.Greeting
import com.example.testkmm.data.User
import com.example.testkmm.di.MultiplatformSDK
import com.example.testkmm.di.userRepository
import com.example.testkmm.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    var users: List<User> by mutableStateOf(listOf())

    init {
        viewModelScope.launch {
            userRepository.getAllUsers().collect {
                if (it != null) {
                    users = it
                }
            }
        }
    }
}