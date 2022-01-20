package com.example.testkmm.data

import com.example.testdatabase.TestDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(TestDatabase.Schema, "TestDatabase.db")
    }
}