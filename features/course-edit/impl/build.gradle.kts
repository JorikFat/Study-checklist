plugins {
    alias(libs.plugins.pavlig43.android.library)
    alias(libs.plugins.pavlig43.compose)

}

android {
    namespace = "ru.pavlig.course_edit"

}

dependencies {

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

}