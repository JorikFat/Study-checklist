package com.pavlig43.convention.extension

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/**
 * Configure base Kotlin with Android options
 */

internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
//    commonExtension.apply {
//        compileSdk = libs.findVersion("compileSdk").get().toString().toInt()
//        lint {
//            checkDependencies = true
//        }
//        defaultConfig {
//            minSdk = libs.findVersion("minSdk").get().toString().toInt()
//        }
//
//        compileOptions {
//            sourceCompatibility = javaVersion
//            targetCompatibility = javaVersion
//        }
//    }
//
//    configureKotlin<KotlinAndroidProjectExtension>()


}

/**
 * Configure base Kotlin options for JVM (non-Android)
 */
internal fun Project.configureKotlinJvm() {
    extensions.configure<JavaPluginExtension> {

//        sourceCompatibility = javaVersion
//        targetCompatibility = javaVersion
    }

    configureKotlin<KotlinJvmProjectExtension>()
}

/**
 * Configure base Kotlin options
 */
private inline fun <reified T : KotlinProjectExtension> Project.configureKotlin() = configure<T> {
    tasks.withType<KotlinCompile>().configureEach {
        compilerOptions {

        }
    }

    when (this) {
        is KotlinAndroidProjectExtension -> compilerOptions
        is KotlinJvmProjectExtension -> compilerOptions
        else -> TODO("Unsupported project extension $this ${T::class}")
    }.apply {
        jvmTarget.set(jvmTarget)

    }

}
