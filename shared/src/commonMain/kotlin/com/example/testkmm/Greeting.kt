package com.example.testkmm

import com.example.testkmm.data.User
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import kotlinx.serialization.Serializable

class Greeting {
    private val httpClient = HttpClient(){
        install(Logging){
            level = LogLevel.ALL
            logger = object : Logger{
                override fun log(message: String){
                    Napier.v(tag = "HTTP Client", message = message)
                }
            }
        }
        install(JsonFeature){
            val json = kotlinx.serialization.json.Json { ignoreUnknownKeys = true }
            serializer = KotlinxSerializer(json)
        }
    }.also { initLogger() }

    suspend fun greeting(): String {
        return getUsers().random().toString()
    }

    private suspend fun getUsers(): List<User>{
        return httpClient.get("https://jsonplaceholder.typicode.com/users")
    }
}






