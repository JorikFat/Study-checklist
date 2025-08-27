plugins {
    alias(libs.plugins.pavlig43.feature)
}

android {
    namespace = "dev.jorik.study_checklist.course_content"

}
dependencies{
    implementation(projects.features.courses)
}

