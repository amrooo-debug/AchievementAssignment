plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)

    // Allows Hilt to generate code inside the app module.
    alias(libs.plugins.ksp)

    // Enables Hilt for MainActivity and MainViewModel.
    alias(libs.plugins.hilt.android)
}

android {
    namespace = "com.example.achievementassignment"

    compileSdk {
        version = release(37) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "com.example.achievementassignment"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner =
            "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false

            proguardFiles(
                getDefaultProguardFile(
                    "proguard-android-optimize.txt"
                ),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    // Gives the app module access to the data and core modules.
    implementation(project(":data"))
    implementation(project(":core"))

    // Allows Hilt annotations such as @AndroidEntryPoint
    // and @HiltViewModel to be used in the app module.
    implementation("com.google.dagger:hilt-android:2.60.1")

    // Generates the code required by Hilt during the build.
    ksp("com.google.dagger:hilt-compiler:2.60.1")

    // Downloads and displays images from API URLs.
    implementation("io.coil-kt.coil3:coil-compose:3.3.0")
    implementation("io.coil-kt.coil3:coil-network-okhttp:3.3.0")

    // Connects ViewModel and LiveData with Jetpack Compose.
    implementation(
        "androidx.lifecycle:lifecycle-viewmodel-compose:2.11.0"
    )
    implementation(
        "androidx.lifecycle:lifecycle-livedata-ktx:2.11.0"
    )
    implementation(
        "androidx.lifecycle:lifecycle-viewmodel-ktx:2.11.0"
    )
    implementation(
        "androidx.compose.runtime:runtime-livedata"
    )

    // Jetpack Compose dependencies.
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Provides Compose preview and inspection tools in debug builds.
    debugImplementation(libs.androidx.compose.ui.tooling)
}