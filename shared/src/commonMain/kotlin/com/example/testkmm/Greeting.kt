package com.example.testkmm

import com.example.testkmm.data.User
import com.example.testkmm.data.UserDataSource
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.Flow

//class Greeting(val userDataSource: UserDataSource) {
//    private val httpClient = HttpClient(){
//        install(Logging){
//            level = LogLevel.ALL
//            logger = object : Logger{
//                override fun log(message: String){
//                    Napier.v(tag = "HTTP Client", message = message)
//                }
//            }
//        }
//        install(JsonFeature){
//            val json = kotlinx.serialization.json.Json { ignoreUnknownKeys = true }
//            serializer = KotlinxSerializer(json)
//        }
//    }.also { initLogger() }
//
////    suspend fun greeting(): Flow<List<User>?> {
////        val users = getUsers()
////       // userDataSource.insertUser(users)
////        return userDataSource.getAllUsersAsFlow()
////    }
//
//    private suspend fun getUsers(): List<User>{
//        return httpClient.get("https://jsonplaceholder.typicode.com/users")
//    }
//}






