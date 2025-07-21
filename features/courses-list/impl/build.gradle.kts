plugins {
    alias(libs.plugins.pavlig43.android.library)
    alias(libs.plugins.pavlig43.compose)
}

android {
    namespace = "ru.pavlig43.courses_list_impl"

}

dependencies {
    androidTestImplementation(libs.androidx.ui.test.junit4)
}