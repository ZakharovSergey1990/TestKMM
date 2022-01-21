package com.example.testkmm.data

import com.example.testdatabase.TestDatabase
import com.example.testkmm.KMMContext
import com.squareup.sqldelight.db.SqlDriver

expect class DriverFactory(context : KMMContext) {
    fun createDriver(): SqlDriver
}

//fun createDatabase(driverFactory): TestDatabase {
//    val driver = driverFactory.createDriver()
//    val database = TestDatabase(driver)

    // Do more work with the database (see below).
//}