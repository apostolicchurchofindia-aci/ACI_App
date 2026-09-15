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

rootProject.name = "ACI_APK"

include(":app")
include(":core-ui")
include(":core-data")
include(":core-domain")
include(":feature-auth")
include(":feature-home")
include(":feature-bible")
include(":feature-songs")
include(":feature-worship")
include(":feature-sermons")
include(":feature-events")
include(":feature-prayer")
include(":feature-sundayschool")
include(":feature-community")
include(":feature-giving")
include(":feature-profile")
include(":feature-admin")
include(":data-content")
