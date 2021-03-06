package com.example.testkmm.repository

import com.example.testkmm.api.TestHttpApi
import com.example.testkmm.data.User
import com.example.testkmm.data.UserDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface UserRepository {
    suspend fun getAllUsers(): List<User>?
    suspend fun insertUser(user: User)
}

class UserRepositoryImpl(
    val userDataSource: UserDataSource,
    val testHttpApi: TestHttpApi
) : UserRepository {
    override suspend fun getAllUsers(): List<User>? {
        var users = userDataSource.getAllUsers()
        if (!users.isNullOrEmpty()) {
            return userDataSource.getAllUsers()
        }
        users = testHttpApi.getUsers()
        users.forEach { insertUser(it) }
        return users
    }

    override suspend fun insertUser(user: User) {
        userDataSource.insertUser(user)
    }
}