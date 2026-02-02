plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose.compiler)
    // Kotlin Android plugin уже встроен в AGP 9.0, не нужно добавлять явно
}

android {
    namespace = "com.perepel.app"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.perepel.app"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            // proguardFiles getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "2.3.0" // Должно совпадать с версией Kotlin
    }

    kotlin {
        jvmToolchain(17)
    }
}

dependencies {
    // === ДОБАВЛЯЕМ BOM KOIN (ВАЖНО: ПЕРВОЙ В СЕКЦИИ KOIN) ===
    implementation(platform(libs.koin.bom))

    // === ЛОКАЛЬНЫЕ МОДУЛИ ПРОЕКТА ===
    implementation(project(":core"))
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":core-ui"))
    implementation(project(":feature-rules"))
    implementation(project(":feature-tracking"))
    implementation(project(":feature-statistics"))
    implementation(project(":feature-notifications"))
    implementation(project(":feature-settings"))

    // === KOIN ДЛЯ ВНЕДРЕНИЯ ЗАВИСИМОСТЕЙ ===
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)  // ⬅️ ДОБАВЛЕНО: если используете Compose в app-модуле

    // === ANDROIDX БАЗОВЫЕ ===
    implementation(libs.androidx.core.ktx)      // ⬅️ ИСПРАВЛЕНО: используем из libs.versions.toml
    implementation("androidx.appcompat:appcompat:1.6.1")

    // === COROUTINES ===
    implementation(libs.kotlinx.coroutines.core) // ⬅️ ИСПРАВЛЕНО: используем из libs.versions.toml

    // === COMPOSE (если используете в app-модуле) ===
    implementation(platform("androidx.compose:compose-bom:2023.10.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")

    // УДАЛЕНА лишняя зависимость kotlin-stdlib с exclude - она не нужна
    // Kotlin stdlib автоматически добавляется плагином Kotlin
}