import com.pavlig43.convention.extension.configureApplication
import com.pavlig43.convention.extension.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

class ApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = libs.plugins.android.application.get().pluginId)
            apply(plugin = libs.plugins.kotlin.android.get().pluginId)

            configureApplication()
//            extensions.configure<BaseAppModuleExtension> {
//                configureApplication()
//                configureKotlinAndroid(this)
//            }
        }


    }
}

