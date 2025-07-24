plugins {
    id("kotlin-sample")
}

android {
    namespace = "com.pavlig43.courceediting"

    defaultConfig {
        applicationId = "com.pavlig43.sample"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation(projects.features.courseEdit.impl)
}