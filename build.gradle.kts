// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.compose) apply false

    //    build-logic
    alias(libs.plugins.pavlig43.application) apply false
    alias(libs.plugins.pavlig43.compose) apply false
    alias(libs.plugins.pavlig43.android.library) apply false
    alias(libs.plugins.pavlig43.android.room) apply false
    alias(libs.plugins.pavlig43.jvm.library) apply false

}