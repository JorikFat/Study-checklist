import com.android.build.api.dsl.ApplicationExtension
import com.android.build.gradle.LibraryExtension
import com.pavlig43.convention.extension.configureCompose
import com.pavlig43.convention.extension.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.findByType

class ComposePlugin:Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            apply(plugin = libs.plugins.kotlin.compose.get().pluginId)

            val applicationExtension = extensions.findByType<ApplicationExtension>()
            if (applicationExtension != null) {
                configureCompose(commonExtension = applicationExtension)
            }
            val libraryExtension = extensions.findByType<LibraryExtension>()
            if (libraryExtension != null) {
                configureCompose(commonExtension = libraryExtension)
            }
        }

    }
}