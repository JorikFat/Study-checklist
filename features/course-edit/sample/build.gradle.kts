plugins {
    alias(libs.plugins.pavlig43.application)
    alias(libs.plugins.pavlig43.compose)
}

android {
    namespace = "com.pavlig43.courceediting"
    defaultConfig {
        applicationId = "com.pavlig43.sample"
        versionCode = 1
        versionName = "1.0"

    }

}

dependencies {
    implementation(projects.features.courseEdit.lib)

}