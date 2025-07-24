plugins {
    id("kotlin-sample")
}

android {
    namespace = "ru.pavlig43.courses_list_sample"

    defaultConfig {
        applicationId = "ru.pavlig43.courses_list_sample"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation(projects.features.coursesList.impl)
}