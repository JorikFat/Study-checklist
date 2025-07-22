import com.pavlig43.convention.extension.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

class FeaturePlugin:Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            apply(plugin = libs.plugins.pavlig43.android.library.get().pluginId)
            apply(plugin = libs.plugins.pavlig43.compose.get().pluginId)
            apply(plugin = libs.plugins.pavlig43.test.feature.get().pluginId)
        }
    }
}