import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    id("com.squareup.sqldelight")
    kotlin("plugin.serialization") version "1.6.10"
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")

}

sqldelight {
    database("TestDatabase") {
        packageName = "com.example.testdatabase"
    }
}

version = "1.0"

kotlin {
    android()

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget = when {
        System.getenv("SDK_NAME")?.startsWith("iphoneos") == true -> ::iosArm64
        System.getenv("NATIVE_ARCH")?.startsWith("arm") == true -> ::iosSimulatorArm64
        else -> ::iosX64
    }

    iosTarget("ios") {}

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        frameworkName = "shared"
        podfile = project.file("../iosApp/Podfile")
    }

    sourceSets {

        val ktorVersion = "1.5.4"
        val napierVersion = "2.3.0"
        val commonMain by getting {
            dependencies {
                //logging
                implementation("io.github.aakira:napier:$napierVersion")

                //ktor
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-logging:$ktorVersion")
                implementation("io.ktor:ktor-client-serialization:$ktorVersion")


                implementation("org.jetbrains.kotlin:kotlin-stdlib-common:1.6.10")
                //implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:1.3.6")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")

                //implementation ("io.ktor:ktor-client-json:1.0.0")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")


               implementation("com.squareup.sqldelight:runtime:1.5.3")
               implementation("com.squareup.sqldelight:coroutines-extensions:1.5.3")

                //di

                implementation("org.kodein.di:kodein-di:7.10.0")

            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-stdlib:1.6.10")

                implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
                //  implementation ("io.ktor:ktor-client-json-jvm:1.0.0")
                //   implementation ("io.ktor:ktor-client-android:1.0.0")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0")
                implementation("com.squareup.sqldelight:android-driver:1.5.3")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }
        val iosMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-ios:$ktorVersion")
                implementation("com.squareup.sqldelight:native-driver:1.5.3")
            }
        }
        val iosTest by getting
    }

}

android {
    compileSdkVersion(31)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(23)
        targetSdkVersion(31)
    }
}


