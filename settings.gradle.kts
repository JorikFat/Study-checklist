enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    includeBuild("build-logic")
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
    }
}

rootProject.name = "Study_checklist"
include(":app")
include(":overview")//TODO: rename to prototype
include(
    ":features:courses-list:sample",
    ":features:courses-list:lib"
)
include(
    ":features:course-content:sample",
    ":features:course-content:impl"//TODO: rename to lib
)
include(
    ":features:course-edit:sample",
    ":features:course-edit:impl"//TODO: rename to lib
)
