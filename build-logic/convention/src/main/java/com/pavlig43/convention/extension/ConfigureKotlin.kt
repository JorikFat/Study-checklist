package com.pavlig43.convention.extension

import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension

internal fun Project.configureKotlinAndroid() {
    androidExtension.apply {
        compileOptions {
            sourceCompatibility = projectJavaVersion
            targetCompatibility = projectJavaVersion
        }
        compileSdk = libs.versions.compileSdk.get().toInt()
        lint {
            checkDependencies = true
        }
        defaultConfig {
            minSdk = libs.versions.minSdk.get().toInt()
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        configureKotlin<KotlinAndroidProjectExtension>()
    }
}


/**
 * Configure base Kotlin options for JVM (non-Android)
 */
internal fun Project.configureKotlinJvm() {
    extensions.configure<JavaPluginExtension> {


        sourceCompatibility = projectJavaVersion
        targetCompatibility = projectJavaVersion
    }
    configureKotlin<KotlinJvmProjectExtension>()
}

/**
 * Configure base Kotlin options
 */
private inline fun <reified T : KotlinProjectExtension> Project.configureKotlin() = configure<T> {
    when (this) {
        is KotlinAndroidProjectExtension -> compilerOptions
        is KotlinJvmProjectExtension -> compilerOptions
        else -> TODO("Unsupported project extension $this ${T::class}")
    }.apply {
        jvmTarget.set(projectJvmTarget)

    }

}
