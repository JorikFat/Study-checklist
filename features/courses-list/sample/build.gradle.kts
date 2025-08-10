import com.pavlig43.convention.extension.libs

plugins {
    alias(libs.plugins.pavlig43.application)
    alias(libs.plugins.pavlig43.compose)
    alias(libs.plugins.koin)
}

android {
    namespace = "ru.pavlig43.courses_list_sample"

    defaultConfig {
        applicationId = "ru.pavlig43.courses_list_sample"
        versionCode = 1
        versionName = "1.0"
        setProperty("archivesBaseName", "StudyChecklist_list-sample v${versionName} [${versionCode}]")
    }


}

dependencies {
    implementation(projects.features.coursesList.lib)

//    implementation(libs.koin.bom)
//    implementation(libs.koin.core)
//    implementation(libs.koin.android)
//    implementation(libs.koin.compose)

}