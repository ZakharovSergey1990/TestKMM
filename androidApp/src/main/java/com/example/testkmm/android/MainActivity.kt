package com.example.testkmm.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testkmm.Greeting
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

suspend fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity(), CoroutineScope {
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tv: TextView = findViewById(R.id.text_view)


        launch(Dispatchers.Main) {
            try {
                tv.text = greet()
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}