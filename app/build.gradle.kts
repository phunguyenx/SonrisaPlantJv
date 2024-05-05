plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.example.sonrisaplantjv"
    compileSdk = 34
    //view bindding
    buildFeatures {
        viewBinding = true
    }
    viewBinding {
        enable = true
    }
    dataBinding{
        enable = true
    }
    defaultConfig {
        applicationId = "com.example.sonrisaplantjv"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation ("com.fasterxml.jackson.core:jackson-annotations:2.13.2")
    implementation ("com.fasterxml.jackson.core:jackson-core:2.13.2")
    implementation ("com.fasterxml.jackson.core:jackson-databind:2.13.2")
    implementation ("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.13.2")

    //gson
    implementation ("com.google.code.gson:gson:2.10.1")

    // Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    // Retrofit with Scalar Converter
    implementation ("com.squareup.retrofit2:converter-scalars:2.9.0")

    //corotine
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")

    //image picker


}