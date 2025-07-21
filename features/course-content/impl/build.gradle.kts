plugins {
    alias(libs.plugins.pavlig43.android.library)
    alias(libs.plugins.pavlig43.compose)
}

android {
    namespace = "dev.jorik.study_checklist.course_content"

}

dependencies {
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

}