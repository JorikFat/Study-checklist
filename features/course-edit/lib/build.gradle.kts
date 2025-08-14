plugins {
    alias(libs.plugins.pavlig43.feature)
}

android {
    namespace = "ru.pavlig.course_edit"

}
dependencies{
    implementation(projects.features.courses)
}
