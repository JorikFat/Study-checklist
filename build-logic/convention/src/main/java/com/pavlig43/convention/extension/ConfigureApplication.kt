package com.pavlig43.convention.extension

import com.android.build.gradle.internal.api.ApkVariantOutputImpl
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

internal fun Project.configureApplication() {
    configureAndroid()
    (androidExtension as BaseAppModuleExtension).apply {
        defaultConfig {

            targetSdk = libs.versions.targetSdk.get().toInt()
            applicationVariants.all {
                val variant = this

                outputs.all {
                    if (this is ApkVariantOutputImpl) {
                        val name = variant.applicationId.substringAfter('.')
                        this.outputFileName =
                            "${name}_${variant.name}_${variant.versionCode}_${variant.versionName}_${getApkBuildTime()}.apk"
                    }
                }
            }

            buildConfigField("String", "VERSION_NAME", "\"$versionName\"")
            buildConfigField("String", "BUILD_TIME", "\"${getBuildConfigTime()}\"")

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

private fun getTime(pattern: String): String {
    val now = ZonedDateTime.now(ZoneId.of("Europe/Moscow"))
    val formatter = DateTimeFormatter.ofPattern(pattern)
    return now.format(formatter)
}

private fun getApkBuildTime(): String {
    return getTime(pattern = "yyyy_MM_dd_HH_mm_ss")
}

private fun getBuildConfigTime(): String {
    return getTime(pattern = "yyyy-MM-dd HH:mm:ss")
}
