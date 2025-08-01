package com.pavlig43.convention.extension

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies


internal fun Project.configureCompose(commonExtension: CommonExtension<*,*,*,*,*,*>){
    commonExtension.apply {
        buildFeatures{
            compose = true
        }
        dependencies{
            val bomPlatform = platform(libs.androidx.compose.bom)
            implementation(bomPlatform)
            implementation(libs.androidx.ui.graphics)
            implementation(libs.androidx.ui.tooling.preview)
            implementation(libs.androidx.material3)
            implementation(libs.androidx.ui.graphics)
            implementation(libs.androidx.material3)

            //TODO удалить после добавления koinViewModel
            implementation(libs.androidx.viewmodel.compose)

            debugImplementation(libs.androidx.ui.tooling)
            debugImplementation(libs.androidx.ui.test.manifest)

        }

    }
}
