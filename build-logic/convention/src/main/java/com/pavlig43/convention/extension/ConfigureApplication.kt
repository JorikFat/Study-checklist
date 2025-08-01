package com.pavlig43.convention.extension

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureApplication() {
    configureAndroid()
    (androidExtension as BaseAppModuleExtension).apply {
        defaultConfig {

            targetSdk = libs.versions.targetSdk.get().toInt()

        }


        buildTypes {
//            debug {
////                signingConfig = signingConfigs.getByName("release")
//                isMinifyEnabled = true
//                isDebuggable = true
//                proguardFiles(
//                    getDefaultProguardFile("proguard-android-optimize.txt"),
//                    "proguard-rules.pro"
//                )
//            }
            release {
//                signingConfig = signingConfigs.getByName("release")
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )

            }
        }
        buildFeatures {
            buildConfig = true
        }
        dependencies{
            implementation(libs.androidx.activity.compose)
        }
    }
}

