plugins {
    alias(libs.plugins.kotlin.jvm)
}

group = "com.perepel.core"
version = "1.0.0"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    // Добавляем BOM Koin для управления версиями
    implementation(platform(libs.koin.bom))

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.koin.core)
}