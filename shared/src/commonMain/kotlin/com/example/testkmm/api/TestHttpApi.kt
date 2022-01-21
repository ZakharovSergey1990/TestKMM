package com.example.testkmm.api

import com.example.testkmm.data.User
import com.example.testkmm.initLogger
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*

interface TestHttpApi {
    suspend fun getUsers(): List<User>
}

class TestHttpApiImpl():TestHttpApi {

    private val httpClient = HttpClient(){
        install(Logging){
            level = LogLevel.ALL
            logger = object : Logger {
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

    override suspend fun getUsers(): List<User> {
        return httpClient.get("https://jsonplaceholder.typicode.com/users")
    }
}