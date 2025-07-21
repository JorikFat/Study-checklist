import org.gradle.api.Plugin
import org.gradle.api.Project


class DetektConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
//        target.plugins.withType(KotlinBasePlugin::class.java) {
//            with(target) {
//                apply(plugin = "io.gitlab.arturbosch.detekt")
//                extensions.configure<DetektExtension>() {
//                    config.setFrom(rootProject.files("default-detekt-config.yml"))
//                    buildUponDefaultConfig = true
//                    autoCorrect = true
//                    parallel = true
//                    ignoreFailures = false
//
//                }
//                with(tasks) {
//                    withType<Detekt> {
//                        exclude {
//                            it.file.invariantSeparatorsPath.contains("/build/generated/")
//                        }
//                        reports{
//                            html.required.set(true)
//                            md.required.set(true)
////                            xml.required.set(true)
////                            txt.required.set(true)
////                            sarif.required.set(true)
//                        }
//                    }
//                    withType<Detekt>().configureEach {
//                        jvmTarget = javaVersion.toString()
//                    }
//                    withType<DetektGenerateConfigTask>().configureEach {
//                        enabled = false
//                    }
//                }
//
//                dependencies {
//                    add("detektPlugins", libs.findLibrary("detekt.formatting").get())
//                }
//                pluginManager.withPlugin("pavlig43.android.compose"){
//                    dependencies{
//                        add("detektPlugins", libs.findLibrary("detekt.compose").get())
//                    }
//                }
//            }
//        }
    }
}