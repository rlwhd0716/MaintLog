plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.plugin)
    alias(libs.plugins.hilt.plugin)
    alias(libs.plugins.ksp.plugin)
    alias(libs.plugins.kapt.plugin)
    alias(libs.plugins.parcelize.plugin)
}

android {
    namespace = "com.github.util"
    compileSdk = rootProject.extra["currentSdk"] as Int


    defaultConfig {
        minSdk = rootProject.extra["minSdk"] as Int

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    buildFeatures {
        dataBinding = true
        viewBinding = true
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = rootProject.extra["JDK-Version"] as JavaVersion
        targetCompatibility = rootProject.extra["JDK-Version"] as JavaVersion
    }

    kotlinOptions {
        jvmTarget = rootProject.extra["JDK-Target"] as String
    }
}

dependencies {
    implementation(libs.bundles.ui)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.bundles.component)
    testImplementation(libs.test.junit)
    androidTestImplementation(libs.android.test.junit)
    androidTestImplementation(libs.android.test.core)

    //hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    //viewModel, livedata
    implementation(libs.bundles.lifecycle)
    ksp(libs.lifecycle.compiler)

    // Retrofit, Okhttp, gson
    implementation(libs.bundles.retrofit)

    // Coroutine
    implementation(libs.bundles.coroutines)

    // location
    implementation(libs.androidsvg)

    implementation(libs.shimmer)

    // room
    implementation(libs.bundles.room)
    ksp(libs.room.compiler)
}