import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    id("kotlin-kapt")
}



dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.0.1")
    implementation ("androidx.core:core-ktx:1.3.2")
    implementation ("androidx.appcompat:appcompat:1.2.0")
    implementation ("com.google.android.material:material:1.3.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation ("androidx.compose.ui:ui:1.2.0-alpha01")
    implementation ("androidx.compose.ui:ui-tooling:1.2.0-alpha01")
    implementation ("androidx.compose.foundation:foundation:1.2.0-alpha01")
    implementation ("androidx.compose.material:material:1.2.0-alpha01")
    implementation ("androidx.compose.material:material-icons-core:1.2.0-alpha01")
    implementation ("androidx.compose.material:material-icons-extended:1.2.0-alpha01")
    implementation ("androidx.compose.runtime:runtime-rxjava2:1.2.0-alpha01")
    implementation ("androidx.appcompat:appcompat:1.4.1")


    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0-rc01")
    implementation ("com.google.dagger:hilt-android:2.40.5")
    kapt("com.google.dagger:hilt-compiler:2.40.5")
}

kapt {
    correctErrorTypes = true
}

android {
    compileSdkVersion(31)
    defaultConfig {
        applicationId = "com.example.testkmm.android"
        minSdkVersion(23)
        targetSdkVersion(31)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
//        kotlinCompilerVersion = "1.3.61-dev-withExperimentalGoogleExtensions-20200129"
//        kotlinCompilerExtensionVersion = "0.1.0-dev07"
        kotlinCompilerExtensionVersion = "1.2.0-alpha01"
    }
}