plugins {
    alias(libs.plugins.pavlig43.application)
    alias(libs.plugins.pavlig43.compose)
    alias(libs.plugins.koin)
}

android {
    namespace = "com.pavlig43.displayingcoursecontent"

    defaultConfig {
        applicationId = "com.pavlig43.sample"
        versionCode = 1
        versionName = "1.0"
        setProperty("archivesBaseName", "StudyChecklist_content-sample v${versionName} [${versionCode}]")
    }
    
}

dependencies {
    implementation(projects.features.courseContent.lib)
}