package com.example.testkmm.data

import com.example.testdatabase.TestDatabase
import com.squareup.sqldelight.db.SqlDriver
import kotlinx.coroutines.flow.Flow

interface UserDataSource {
    fun getAllUsers(): Flow<List<User>>

   suspend fun deleteUserById(id: Long)
}

class UserDataSourceImpl(private val db: TestDatabase) : UserDataSource{

   override fun getAllUsers(): Flow<List<User>>{
      TODO()
   }

   override suspend fun deleteUserById(id: Long){

   }
}