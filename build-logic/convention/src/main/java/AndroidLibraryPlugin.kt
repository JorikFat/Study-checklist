import com.android.build.api.dsl.LibraryExtension
import com.pavlig43.convention.extension.configureAndroid
import com.pavlig43.convention.extension.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

class AndroidLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = libs.plugins.android.library.get().pluginId)
            apply(plugin = libs.plugins.kotlin.android.get().pluginId)
            configureAndroid()

            extensions.configure<LibraryExtension> {
                defaultConfig {
                    consumerProguardFiles("consumer-rules.pro")
                }

            }
        }

    }
}