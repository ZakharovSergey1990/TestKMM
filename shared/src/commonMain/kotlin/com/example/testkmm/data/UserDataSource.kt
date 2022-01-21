package com.example.testkmm.data

import com.example.testdatabase.PersonEntityQueries
import com.example.testdatabase.TestDatabase
import com.example.testdatabase.Users
import com.squareup.sqldelight.ColumnAdapter
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

interface UserDataSource {
    fun getAllUsers(): Flow<List<User>?>

    suspend fun deleteUserById(id: Long)

    suspend fun insertUsers(list: List<User>)
}

class UserDataSourceImpl(private val driver: SqlDriver) : UserDataSource {

    private val database = createDatabase(driver)

    private var queries: PersonEntityQueries = database.personEntityQueries

    override fun getAllUsers(): Flow<List<User>> {

        return queries.getAllUsers().asFlow().mapToList().map { list -> mapUsersToUserList(list) }
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


    private fun createDatabase(driver: SqlDriver): TestDatabase {

        val addressAdapter = object : ColumnAdapter<Address, String> {
            override fun decode(databaseValue: String): Address {
                return Json.decodeFromString<Address>(databaseValue)
            }

            override fun encode(value: Address): String {
                return Json.encodeToString(value)
            }

        }

        val companyAdapter = object : ColumnAdapter<Company, String> {
            override fun decode(databaseValue: String): Company {
                return Json.decodeFromString<Company>(databaseValue)
            }

            override fun encode(value: Company): String {
                return Json.encodeToString(value)
            }
        }

        return TestDatabase(
            driver,
            Users.Adapter(addressAdapter = addressAdapter, companyAdapter = companyAdapter)
        )
    }
}