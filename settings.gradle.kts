pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Project2026"
include(":app")
include(":core")
include(":domain")
include(":data")
include(":core-ui")
include(":feature-rules")
include(":feature-tracking")
include(":feature-statistics")
include(":feature-notifications")
include(":feature-settings")