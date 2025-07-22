plugins {
    alias(libs.plugins.pavlig43.application)
    alias(libs.plugins.pavlig43.compose)
    alias(libs.plugins.pavlig43.koin)
}

android {
    namespace = "ru.pavlig43.overview"

    defaultConfig {
        applicationId = "ru.pavlig43.overview"
        versionCode = 1
        versionName = "1.0"
    }
}
