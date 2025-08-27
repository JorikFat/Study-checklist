plugins {
    alias(libs.plugins.pavlig43.feature)
}

android {
    namespace = "ru.pavlig43.courses_list_impl"

}
dependencies{
    implementation(projects.features.courses)
}