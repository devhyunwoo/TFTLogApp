val snapshotVersion : String? = "14101258"
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
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            snapshotVersion?.let {
                url = uri("https://androidx.dev/snapshots/builds/$it/artifacts/repository")
            }
        }
    }
}

rootProject.name = "TFT_Log"
include(":app")
include(":data")
