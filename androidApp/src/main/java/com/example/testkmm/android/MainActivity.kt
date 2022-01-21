package com.example.testkmm.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testkmm.Greeting
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.testkmm.data.DriverFactory
import com.example.testkmm.data.UserDataSourceImpl
import com.example.testkmm.di.MultiplatformSDK
import com.example.testkmm.di.userRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainPage(vm = hiltViewModel())
        }
    }
}

@Composable
fun MainPage(vm: MainViewModel) {

    Surface {
        Text(text = vm.users.toString())

    }
}
