import com.pavlig43.convention.extension.configureKotlinJvm
import com.pavlig43.convention.extension.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

class JvmLibraryPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = libs.plugins.kotlin.android.get().pluginId)
            configureKotlinJvm()


        }
    }
}