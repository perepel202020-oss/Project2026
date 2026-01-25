plugins {
    kotlin("jvm")
}

group = "com.perepel.domain"
version = "1.0.0"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(project(":core"))
    implementation(kotlin("stdlib"))
    implementation(project(":core"))
    implementation(libs.koin.core)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
}