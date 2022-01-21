package com.example.testkmm.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testkmm.Greeting
import android.widget.TextView
import android.widget.Toast
import com.example.testdatabase.TestDatabase
import com.example.testkmm.data.DriverFactory
import com.example.testkmm.data.User
import com.example.testkmm.data.UserDataSource
import com.example.testkmm.data.UserDataSourceImpl
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext

//suspend fun greet(userDataSource: UserDataSource): Flow<List<User>?> {
//    return Greeting(userDataSource).greeting()
//}

class MainActivity : AppCompatActivity(), CoroutineScope {

   // val Greeting = com.example.testkmm.Greeting()

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val driver = DriverFactory(this).createDriver()

        val userDataSource = UserDataSourceImpl(driver)



        setContentView(R.layout.activity_main)
        val tv: TextView = findViewById(R.id.text_view)


        launch(Dispatchers.Main) {
            try {
               Greeting(userDataSource).greeting().collect{
                    tv.text = it.toString()
                }
              //  tv.text = greet()
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}
