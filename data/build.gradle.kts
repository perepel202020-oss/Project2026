plugins {
    kotlin("jvm")
}

group = "com.perepel.data"
version = "1.0.0"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation(project(":domain"))
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
    
    // �������� libs.koin.core �� ����� �����������
    // implementation(libs.koin.core)
    implementation("io.insert-koin:koin-core:3.5.0")
}
