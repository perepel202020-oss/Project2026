plugins {
    id("com.android.library")
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.perepel.statistics"
    compileSdk = 35

    defaultConfig {
        minSdk = 26
    }

    buildFeatures {
        viewBinding = true
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    // === ДОБАВЛЯЕМ BOM KOIN (ВАЖНО: ПЕРВОЙ В СЕКЦИИ KOIN) ===
    implementation(platform(libs.koin.bom))

    // === ЗАВИСИМОСТИ ОТ ДРУГИХ МОДУЛЕЙ ПРОЕКТА ===
    implementation(project(":core"))
    implementation(project(":core-ui"))
    implementation(project(":domain"))   // Для доступа к доменным классам статистики
    implementation(project(":data"))     // Для доступа к данным и репозиториям

    // === KOIN ДЛЯ ВНЕДРЕНИЯ ЗАВИСИМОСТЕЙ ===
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)  // ⬅️ ИЗМЕНЕНИЕ: было android.compose, стало androidx.compose

    // === ANDROIDX (БАЗОВЫЕ) ===
    implementation(libs.androidx.core.ktx)      // ⬅️ ИЗМЕНЕНИЕ: используем из libs.versions.toml
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")

    // === COMPOSE ===
    implementation(platform("androidx.compose:compose-bom:2023.10.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")

    // === ДОПОЛНИТЕЛЬНЫЕ ЗАВИСИМОСТИ ДЛЯ СТАТИСТИКИ ===
    // implementation("com.github.PhilJay:MPAndroidChart:v3.1.0") // Для графиков и диаграмм
    // implementation("com.google.accompanist:accompanist-pager:0.32.0") // Для постраничного просмотра статистики
    // implementation("androidx.compose.material:material-icons-extended") // Дополнительные иконки
}