plugins {
    kotlin("jvm")
}

group = "com.perepel.coreui"
version = "1.0.0"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(project(":core"))
    implementation(kotlin("stdlib"))

    // Для будущей Android реализации (пока можно закомментировать)
    // implementation("androidx.compose.ui:ui:1.5.4")
    // implementation("androidx.compose.material3:material3:1.1.2")
}