package com.example.testkmm

expect class Platform() {
    val platform: String
}

expect fun initLogger()