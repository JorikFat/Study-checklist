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
        register("roomPlugin") {
            id = libs.plugins.room.get().pluginId
            implementationClass = "RoomPlugin"
        }

        register("JvmLibraryPlugin") {
            id = libs.plugins.pavlig43.jvm.library.get().pluginId
            implementationClass = "JvmLibraryPlugin"
        }

        register("detekt") {
            id = libs.plugins.pavlig43.detekt.get().pluginId
            implementationClass = "DetektPlugin"
        }
        register("testFeature"){
            id = libs.plugins.pavlig43.test.feature.get().pluginId
            implementationClass = "TestFeaturePlugin"
        }
        register("featurePlugin"){
         id = libs.plugins.pavlig43.feature.get().pluginId
         implementationClass = "FeaturePlugin"
        }

        register("koinPlugin") {
            id = libs.plugins.koin.get().pluginId
            implementationClass = "KoinPlugin"
        }

    }
}
