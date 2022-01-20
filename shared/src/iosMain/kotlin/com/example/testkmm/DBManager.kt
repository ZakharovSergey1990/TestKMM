package com.example.testkmm


import com.example.testdatabase.TestDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import kotlin.native.concurrent.AtomicReference
import kotlin.native.concurrent.freeze

actual object DBManager {

    // Take consideration of the specialty of IOS, in order to ensure the multi-threaded sharing, you need to use AtomicReference
    private val driverRef = AtomicReference<SqlDriver?>(null)
    private val dbRef = AtomicReference<TestDatabase?>(null)

    private fun dbSetup(driver: SqlDriver) {
        val db = TestDatabase(driver)
        // After initialization, immediately freeze
        driverRef.value = driver.freeze()
        dbRef.value = db.freeze()
    }

    fun dbClear() {
        driverRef.value?.close()
        dbRef.value = null
        driverRef.value = null
    }

    // OC, SWIFT calls this method to initialize
    fun defaultDriver() {
        dbSetup(NativeSqliteDriver(Schema, DB_NAME))
    }

    actual fun getInstance(): TestDatabase? {
        return dbRef.value
    }
}