import org.jetbrains.kotlin.kapt3.base.Kapt.kapt
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    android.buildFeatures.buildConfig = true

    namespace = "com.njm.mobilenewsapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.njm.mobilenewsapp"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        val properties = Properties()
        properties.load(project.rootProject.file("local.properties").inputStream())

        buildConfigField(type = "String", name = "API_KEY_THE_GUARDIAN", value = "\"${properties.getProperty("API_KEY_THE_GUARDIAN")}\"")
        buildConfigField(type = "String", name = "BASE_URL_THE_GUARDIAN", value = "\"${properties.getProperty("BASE_URL_THE_GUARDIAN")}\"")

        buildConfigField(type = "String", name = "API_KEY_NEW_YORK_TIMES", value = "\"${properties.getProperty("API_KEY_NEW_YORK_TIMES")}\"")
        buildConfigField(type = "String", name = "BASE_URL_NEW_YORK_TIMES", value = "\"${properties.getProperty("BASE_URL_NEW_YORK_TIMES")}\"")

        buildConfigField(type = "String", name = "API_KEY_NEWS", value = "\"${properties.getProperty("API_KEY_NEWS")}\"")
        buildConfigField(type = "String", name = "BASE_URL_NEWS", value = "\"${properties.getProperty("BASE_URL_NEWS")}\"")


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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}

dependencies {

    implementation("com.google.accompanist:accompanist-swiperefresh:0.35.0-alpha")

    implementation("io.coil-kt:coil-compose:2.5.0")
    val work_version = "2.8.0"
    // Kotlin + coroutines
    implementation("androidx.work:work-runtime-ktx:$work_version")

    val lifecycle_version = "2.7.0"
    val arch_version = "2.1.0"
    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    // ViewModel utilities for Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycle_version")
    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
    // Lifecycles only (without ViewModel or LiveData)
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")

    implementation ("androidx.compose.runtime:runtime-livedata:1.6.0")

    implementation ("androidx.compose.runtime:runtime:1.6.0")

    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.0-beta01")


    implementation("androidx.compose.material:material:1.3.1")

    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")
    implementation ("androidx.hilt:hilt-work:1.1.0-alpha01")
    kapt ("androidx.hilt:hilt-compiler:1.1.0-alpha01")


    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")


    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3-android:1.2.0-alpha10")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}
