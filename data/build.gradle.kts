plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.google.ksp)
}

android {
    namespace = "com.yourproject.data"
    compileSdk = 35

    defaultConfig {
        minSdk = 26
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    // Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)

    // GSON для конвертеров Room
    implementation("com.google.code.gson:gson:2.10.1")

    // Java Time для Android (если minSdk < 26)
    implementation("com.jakewharton.threetenabp:threetenabp:1.4.6")
}