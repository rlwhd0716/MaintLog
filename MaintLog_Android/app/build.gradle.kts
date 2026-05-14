plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.plugin)
    alias(libs.plugins.hilt.plugin)
    alias(libs.plugins.ksp.plugin)
    alias(libs.plugins.kapt.plugin)
}

android {
    namespace = "com.github.maintlog_android"
    compileSdk = rootProject.extra["currentSdk"] as Int

    defaultConfig {
        applicationId = "com.github.maintlog_android"
        minSdk = rootProject.extra["minSdk"] as Int
        targetSdk = rootProject.extra["currentSdk"] as Int
        versionName = "1.0"
        versionCode = 1

//        // setProperty("archivesBaseName", "MaintLog_V${versionName}_C${versionCode}")
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
        sourceCompatibility = rootProject.extra["JDK-Version"] as JavaVersion
        targetCompatibility = rootProject.extra["JDK-Version"] as JavaVersion
    }

    kotlinOptions {
        jvmTarget = rootProject.extra["JDK-Target"] as String
    }
    buildFeatures {
        dataBinding = true
        viewBinding = true
        buildConfig = true
    }

    flavorDimensions("server")
    productFlavors {
        create("dev") {
            dimension = "server"
            buildConfigField("String", "API_SERVER","\"http://192.168.0.1:19000/API/\"") // 개발서버
            buildConfigField("String", "PUBLIC_DATA_API","\"https://apis.data.go.kr/\"") // 공공데이터포털
            buildConfigField("String", "PUBLIC_DATA_API_KEY","\"U5mfZDHGxCsECNCpjDLWFUFYis7y2%2B5%2B8Noud4I3hdF6uTCa1Jmezxb6TMhsAOcgu2H4mbc99WDimxGn4QLM2g%3D%3D\"") // 공공데이터포털 API KEY
        }

        create("real") {
            dimension = "server"
            buildConfigField("String", "API_SERVER","\"https://www.domain.com/API/\"") // 운영서버
            buildConfigField("String", "PUBLIC_API_PORTAL","\"https://apis.data.go.kr/\"") // 공공데이터포털
            buildConfigField("String", "PUBLIC_API_PORTAL_KEY","\"U5mfZDHGxCsECNCpjDLWFUFYis7y2%2B5%2B8Noud4I3hdF6uTCa1Jmezxb6TMhsAOcgu2H4mbc99WDimxGn4QLM2g%3D%3D\"") // 공공데이터포털 API KEY
        }
    }
}

dependencies {
    // Clean Architecture
    implementation(project(mapOf("path" to ":data")))
    implementation(project(mapOf("path" to ":domain")))
    implementation(project(mapOf("path" to ":util")))

    // project default
    implementation(libs.bundles.ui)
    implementation(libs.bundles.nav)
    implementation(libs.bundles.component)
    testImplementation(libs.test.junit)
    androidTestImplementation(libs.android.test.junit)
    androidTestImplementation(libs.android.test.core)
    implementation(libs.git.floatingsearchview)

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

    // etc
    implementation(libs.glide.core)
    ksp(libs.glide.compiler)
    implementation(libs.shimmer)
}