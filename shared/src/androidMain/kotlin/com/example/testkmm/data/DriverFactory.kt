package com.example.testkmm.data

import android.content.Context
import com.example.testdatabase.TestDatabase
import com.example.testkmm.KMMContext
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DriverFactory actual constructor (val context : KMMContext) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(TestDatabase.Schema, (context as Context) , "TestDatabase.db")
    }
}