plugins {
    kotlin("jvm")
    application
}

group = "com.perepel.app"
version = "1.0.0"

application {
    mainClass.set("com.perepel.app.TestAllFiveFeaturesKt")
}

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
    implementation(project(":feature-rules"))
    implementation(project(":feature-tracking"))
    implementation(project(":feature-statistics"))
    implementation(project(":feature-notifications"))
    implementation(project(":feature-settings"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
    implementation(libs.koin.core)
}