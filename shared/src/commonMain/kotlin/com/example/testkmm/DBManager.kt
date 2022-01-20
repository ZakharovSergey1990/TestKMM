package com.example.testkmm

import com.example.testdatabase.TestDatabase
import com.squareup.sqldelight.db.SqlDriver

const val DB_NAME = "TestDatabase.db"

expect object DBManager{
    fun getInstance(): TestDatabase?
}

object Schema : SqlDriver.Schema by TestDatabase.Schema{
    override fun create(driver: SqlDriver) {
        TestDatabase.Schema.create(driver)
    }
}