plugins {
    alias(libs.plugins.pavlig43.application)
    alias(libs.plugins.pavlig43.compose)
    alias(libs.plugins.kotlin.plugin.serialization)
    alias(libs.plugins.koin)
}

android {
    namespace = "ru.pavlig43.prototype"

    defaultConfig {
        applicationId = "ru.pavlig43.prototype"
        versionCode = 1
        versionName = "1.0"
        setProperty("archivesBaseName", "StudyChecklist_prototype v${versionName} [${versionCode}]")
    }

}
dependencies {

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.navigation.compose)

    implementation(projects.features.coursesList.lib)
    implementation(projects.features.courseEdit.lib)
    implementation(projects.features.courseContent.lib)
    implementation(projects.features.courses)
}
