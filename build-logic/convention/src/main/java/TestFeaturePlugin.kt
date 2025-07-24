import com.pavlig43.convention.extension.configureTest
import org.gradle.api.Plugin
import org.gradle.api.Project

class TestFeaturePlugin:Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
        configureTest()
        }
    }
}