plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.proffer"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.proffer"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(project(":domain"))
    implementation(project(":data"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.androidx.navigation.compose)

 
    //variable
    val lifecycle_version = "2.8.7"
    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycle_version")
    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
    //Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    //hilt
    implementation ("com.google.dagger:hilt-android:2.52")
    kapt ("com.google.dagger:hilt-compiler:2.52")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    //Glide
    implementation ("com.github.bumptech.glide:compose:1.0.0-beta01")
    //httploginginspector
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    //Navigation
    dependencies {
        val nav_version = "2.8.1"
        implementation("androidx.navigation:navigation-compose:$nav_version")

        //data store
        implementation ("androidx.datastore:datastore-preferences:1.0.0")

        implementation(libs.ok2curl)
        implementation("org.jetbrains.kotlinx:kotlinx-metadata-jvm:0.7.0")



        // Glide (Removed Duplicate)
        implementation("com.github.bumptech.glide:glide:4.13.0")
//    kapt("com.github.bumptech.glide:compiler:4.13.0")

            // OkHttp
            implementation ("com.squareup.okhttp3:okhttp:4.9.3")

            // Kotlin Coroutines (لـ runBlocking)
            implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")


            // Logger ( Timber)
            implementation ("com.orhanobut:logger:2.2.0")
        // Accompanist
        implementation ("com.google.accompanist:accompanist-systemuicontroller:0.32.0")
        // Coil (Fixed Dependency Name)
        implementation("io.coil-kt:coil-compose:2.2.2")
        }

    }

