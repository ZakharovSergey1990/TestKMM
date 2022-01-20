package com.example.testkmm.data

import android.content.Context
import com.example.testdatabase.TestDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(TestDatabase.Schema, context, "TestDatabase.db")
    }
}