import com.pavlig43.convention.extension.implementation
import com.pavlig43.convention.extension.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class KoinPlugin:Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            dependencies{
                implementation(libs.koin.core)
                implementation(libs.koin.android)
                implementation(libs.koin.compose)
            }
        }
    }
}