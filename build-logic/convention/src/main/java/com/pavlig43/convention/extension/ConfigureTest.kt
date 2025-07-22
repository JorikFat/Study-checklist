package com.pavlig43.convention.extension

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureTest(){
    androidExtension.apply {
        dependencies {
            testImplementation(libs.junit)
            androidTestImplementation(libs.androidx.junit)
            androidTestImplementation(libs.androidx.espresso.core)
            androidTestImplementation(libs.androidx.ui.test.junit4)
        }
    }
}