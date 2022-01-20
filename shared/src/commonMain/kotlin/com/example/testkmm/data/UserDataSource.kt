package com.example.testkmm.data

import com.example.testdatabase.TestDatabase
import com.example.testdatabase.Users
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

interface UserDataSource {
    fun getAllUsers(): Flow<List<User>?>

    suspend fun deleteUserById(id: Long)

    suspend fun insertUsers(list: List<User>)
}

class UserDataSourceImpl(private val db: TestDatabase) : UserDataSource {

    private val queries = db.personEntityQueries

    override fun getAllUsers(): Flow<List<User>?> {

        return queries.getAllUsers().asFlow().mapToList()
            .map { list -> mapUsersToUserList(list) }
    }

    override suspend fun deleteUserById(id: Long) {

    }

    override suspend fun insertUsers(list: List<User>) {
        withContext(Dispatchers.Default) {
            list.forEach {
                queries.insertUser(
                    it.id,
                    it.name,
                    it.username,
                    it.email,
                    it.address,
                    it.phone,
                    it.website,
                    it.company
                )
            }
        }
    }


    private fun mapUsersToUserList(list: List<Users>): List<User> {
        return list.map {
            User(
                id = it.id,
                name = it.name,
                username = it.username,
                email = it.email,
                address = it.address,
                phone = it.phone,
                website = it.website,
                company = it.company
            )
        }
    }

}