plugins {
    alias(libs.plugins.pavlig43.application)
    alias(libs.plugins.pavlig43.compose)
    alias(libs.plugins.kotlin.plugin.serialization)
}

android {
    namespace = "ru.pavlig43.overview"

    defaultConfig {
        applicationId = "ru.pavlig43.overview"
        versionCode = 1
        versionName = "1.0"
    }

}
dependencies {

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.navigation.compose)

    implementation(projects.features.coursesList.lib)
    implementation(projects.features.courseEdit.lib)
    implementation(projects.features.courseContent.lib)
}
