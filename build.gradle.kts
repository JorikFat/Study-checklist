import com.pavlig43.convention.extension.libs

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.kotlin.plugin.serialization) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.androidx.room) apply false

    //    build-logic
    alias(libs.plugins.pavlig43.application) apply false
    alias(libs.plugins.pavlig43.compose) apply false
    alias(libs.plugins.pavlig43.android.library) apply false
    alias(libs.plugins.room) apply false
    alias(libs.plugins.pavlig43.jvm.library) apply false
    alias(libs.plugins.pavlig43.test.feature) apply false
    alias(libs.plugins.pavlig43.feature) apply false

    alias(libs.plugins.koin) apply false

}
allprojects.onEach { project ->
    project.afterEvaluate {
        apply(plugin = libs.plugins.pavlig43.detekt.get().pluginId)

    }
}