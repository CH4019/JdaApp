@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    id("com.google.dagger.hilt.android")
    kotlin("plugin.serialization") version "1.8.10"
    kotlin("kapt")
}

android {
    namespace = "com.ch4019.jdaapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ch4019.jdaapp"
        minSdk = 29
        targetSdk = 34
        versionCode = 3
        versionName = "1.0.2-beta"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
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
        kotlinCompilerExtensionVersion = "1.4.8"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)

    implementation(libs.datastore.preferences)
    implementation(libs.datastore.preferences.core)
    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.hilt.navigation.compose)

    implementation(libs.androidx.compose.material.iconsExtended)
    implementation(libs.accompanist.webview)
    //implementation(libs.core.splashscreen)
    implementation(libs.navigation.compose)
    implementation(libs.coil.compose)
    implementation(libs.lifecycle.viewmodel.ktx)

    implementation(libs.okhttp)
    implementation(libs.rxhttp)
    implementation(libs.converter.serialization)
    kapt(libs.rxhttp.compiler)

    debugImplementation(libs.monitor)
    releaseImplementation(libs.monitor.no.op)
    implementation(libs.jsoup)
}
