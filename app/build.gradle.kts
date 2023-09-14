plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-android")
    id ("kotlin-parcelize")
    id ("kotlin-kapt")
}

android {
    namespace = "com.pack.projectmanagementapp"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.pack.projectmanagementapp"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures{
        dataBinding = true
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
}

dependencies {
    var lifecycleVersion = "2.3.1"
    var roomVersion="2.5.2"
    var coroutinesVersion = "1.5.0"



//    retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.5.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.5.0")

//room
    implementation ("androidx.room:room-ktx:$roomVersion")
    kapt ("androidx.room:room-compiler:$roomVersion")
    androidTestImplementation ("androidx.room:room-testing:$roomVersion")

    // Lifecycle components
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")
    implementation ("androidx.lifecycle:lifecycle-common-java8:$lifecycleVersion")

    implementation ("androidx.activity:activity-ktx:1.7.2")
    implementation ("com.squareup.picasso:picasso:2.71828")


    // Kotlin Coroutines
    api ("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    api ("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")


    implementation ("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")






//    default dependencies
    
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}