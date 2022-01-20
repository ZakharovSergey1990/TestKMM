buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
        classpath("com.android.tools.build:gradle:7.1.0-alpha03")
        classpath("com.squareup.sqldelight:gradle-plugin:1.5.3")

    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
//        mavenLocal()
//        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}