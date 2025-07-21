import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}
group = "com.pavlig43.buildlogic"
private val projectJavaVersion: JavaVersion = JavaVersion.toVersion(libs.versions.java.get())

java {
    sourceCompatibility = projectJavaVersion
    targetCompatibility = projectJavaVersion
}
kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.fromTarget(projectJavaVersion.toString()))
    }
}
tasks.withType<JavaCompile> {
    sourceCompatibility = libs.versions.java.get()
    targetCompatibility = libs.versions.java.get()
}


dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)
    compileOnly(libs.detekt.gradlePlugin)
    // Workaround for version catalog working inside precompiled scripts
    // Issue - https://github.com/gradle/gradle/issues/15383
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}
gradlePlugin {
    plugins {
        register("ApplicationPlugin") {
            id = libs.plugins.pavlig43.application.get().pluginId
            implementationClass = "ApplicationPlugin"
        }
        register("ComposePlugin") {
            id = libs.plugins.pavlig43.compose.get().pluginId
            implementationClass = "ComposePlugin"

        }
        register("AndroidLibraryPlugin") {
            id = libs.plugins.pavlig43.android.library.get().pluginId
            implementationClass = "AndroidLibraryPlugin"
        }
        register("androidRoom") {
            id = libs.plugins.pavlig43.android.room.get().pluginId
            implementationClass = "AndroidRoomConventionPlugin"
        }

        register("jvmLibrary") {
            id = libs.plugins.pavlig43.jvm.library.get().pluginId
            implementationClass = "JvmLibraryConventionPlugin"
        }

        register("detekt") {
            id = libs.plugins.pavlig43.detekt.get().pluginId
            implementationClass = "DetektConventionPlugin"
        }
    }
}
