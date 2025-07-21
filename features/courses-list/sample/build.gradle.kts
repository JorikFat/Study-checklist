plugins {
    alias(libs.plugins.pavlig43.application)
    alias(libs.plugins.pavlig43.compose)
}

android {
    namespace = "ru.pavlig43.courses_list_sample"

    defaultConfig {
        applicationId = "ru.pavlig43.courses_list_sample"
        versionCode = 1
        versionName = "1.0"
    }


}

dependencies {
    implementation(projects.features.coursesList.impl)

}