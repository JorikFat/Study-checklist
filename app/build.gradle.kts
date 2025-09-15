plugins {
    alias(libs.plugins.pavlig43.application)
    alias(libs.plugins.pavlig43.compose)
    alias(libs.plugins.kotlin.plugin.serialization)
    alias(libs.plugins.koin)
    alias(libs.plugins.room)
}

android {
    namespace = "dev.jorik.study_checklist"

    defaultConfig {
        applicationId = "dev.jorik.study_checklist"
        versionCode = 2
        versionName = "1.0.1"
        setProperty("archivesBaseName", "StudyChecklist v${versionName} [${versionCode}]")
    }


}

dependencies {

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.navigation.compose)
    implementation(libs.androidx.ui.test.junit4)

    implementation(projects.features.coursesList.lib)
    implementation(projects.features.courseEdit.lib)
    implementation(projects.features.courseContent.lib)
    implementation(projects.features.courses)
}