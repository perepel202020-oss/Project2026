pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_PROJECT)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Project2026"
include(":app")
include(":core")
include(":core-ui")
include(":domain")
include(":data")
include(":feature-rules")
include(":feature-tracking")
include(":feature-statistics")
include(":feature-notifications")
include(":feature-settings")
