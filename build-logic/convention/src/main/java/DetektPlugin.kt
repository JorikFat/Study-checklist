import com.pavlig43.convention.extension.detektPlugins
import com.pavlig43.convention.extension.libs
import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektGenerateConfigTask
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.plugin.KotlinBasePlugin

class DetektPlugin : Plugin<Project> {
    override fun apply(target: Project) {

        target.plugins.withType(KotlinBasePlugin::class.java) {
            with(target) {
                apply(plugin = libs.plugins.detekt.get().pluginId)

                extensions.configure<DetektExtension>() {
                    config.setFrom(rootProject.files("default-detekt-config.yml"))
                    buildUponDefaultConfig = false
                    autoCorrect = true
                    parallel = true
                    ignoreFailures = false

                }
                with(tasks) {
                    withType<Detekt> {

                        exclude {
                            it.file.invariantSeparatorsPath.contains("/build/generated/")
                        }

                        reports {
                            html.required.set(true)
                            md.required.set(true)

                        }
                    }
                    withType<Detekt>().configureEach {
                        jvmTarget = libs.versions.java.get().toString()
                    }
                    withType<DetektGenerateConfigTask>().configureEach {
                        enabled = false
                    }
                }

                dependencies {
                    detektPlugins(libs.detekt.formatting)

                }
                pluginManager.withPlugin(libs.plugins.pavlig43.compose.get().pluginId) {
                    dependencies {
                        detektPlugins(libs.detekt.compose)
                    }
                }

            }
        }
    }
}