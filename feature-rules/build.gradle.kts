plugins {
    id("com.android.library")
    alias(libs.plugins.compose.compiler)

}

android {
    namespace = "com.perepel.rules" // Проверьте, должно быть "com.perepel.feature.rules"?
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

    // === ОБЯЗАТЕЛЬНЫЕ ЗАВИСИМОСТИ ОТ ДРУГИХ МОДУЛЕЙ ===
    implementation(project(":core"))
    implementation(project(":domain"))   // <-- ДОБАВЬТЕ ЭТУ СТРОКУ! Для Rule, GetRulesUseCase
    implementation(project(":core-ui"))

    // === KOIN ДЛЯ DI ===
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)    // ⬅️ ИЗМЕНЕНИЕ: было android.compose, стало androidx.compose

    // === ANDROIDX (базовые) ===
    implementation(libs.androidx.core.ktx)        // ⬅️ ИЗМЕНЕНИЕ: используем из libs.versions.toml
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")

    // === COMPOSE ===
    implementation(platform("androidx.compose:compose-bom:2023.10.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0") // Для ViewModel в Compose

    // Если нужны тесты с Koin:
    // testImplementation(libs.koin.test)
}