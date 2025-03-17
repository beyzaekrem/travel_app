plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.travel_app"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.travel_app"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        viewBinding = true  // ViewBinding'i aktif edelim (Harita için lazım olabilir)
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


    // **Google Play Servisleri**
    implementation("com.google.android.gms:play-services-basement:18.2.0")

    // **Gelişmiş Harita İşlevselliği için Maps SDK**
    implementation("com.google.maps.android:android-maps-utils:2.2.3")
}
