package com.example.testkmm

import android.content.Context
import com.example.testdatabase.TestDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import java.lang.ref.WeakReference

actual object DBManager {

    // Use weak references to reference context to prevent memory disclosure
    // Can also consider using Application Context in the self-built tool class
    var contextRef = WeakReference<Context?>(null)

    private var driverRef: SqlDriver? = null
    private var dbRef: TestDatabase? = null

    private val ready: Boolean
        get() = driverRef != null

    private fun dbSetup(driver: SqlDriver) {
        val db = TestDatabase(driver)
        driverRef = driver
        dbRef = db
    }

    // Clear can be called in the appropriate time, release memory
    fun dbClear() {
        driverRef?.close()
        dbRef = null
        driverRef = null
        contextRef = null
    }

    @JvmStatic
    actual fun getInstance(): TestDatabase? {
        if (!ready) {
            val ctx = contextRef.get() ?: return null
            // android uses Androidsqlitedriver
            dbSetup(AndroidSqliteDriver(Schema, ctx, name = DB_NAME))
        }
        return dbRef
    }
}