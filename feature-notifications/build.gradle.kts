plugins {
    kotlin("jvm")
}

group = "com.perepel.feature.notifications"
version = "1.0.0"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(project(":core"))
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":core-ui"))
    implementation(kotlin("stdlib"))
    implementation(libs.koin.core)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
}