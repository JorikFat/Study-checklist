package ru.pavlig43.prototype.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.jorik.study_checklist.course_content.ui.DisplayingCourseContentScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import ru.pavlig.course_edit.ui.Course
import ru.pavlig.course_edit.ui.CourseEditingLayout
import ru.pavlig.course_edit.ui.CourseEditingViewModel
import ru.pavlig.course_edit.ui.Lesson
import ru.pavlig43.courses_list_impl.ui.CoursesScreen
import ru.pavlig43.prototype.navigation.destination.Destination
import ru.pavlig43.prototype.navigation.destination.Overview
import ru.pavlig43.prototype.ui.OverViewScreen

@Composable
fun NavigationHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Overview, modifier = modifier) {
        composable<Overview>{
            OverViewScreen(
                onDestination = {destination->
                    when(destination){
                        Destination.Content -> navController.navigate(Destination.Content)
                        Destination.Courses -> navController.navigate(Destination.Courses)
                        Destination.Create -> navController.navigate(Destination.Create)
                        Destination.Edit -> navController.navigate(Destination.Edit)
                    }
                }
            )
        }
        composable<Destination.Courses> {
            CoursesScreen(viewModel = koinViewModel())
        }
        composable<Destination.Edit> {
            CourseEditingScreen("")
        }
        composable<Destination.Content> {
            DisplayingCourseContentScreen(viewModel = koinViewModel())
        }
        composable<Destination.Create> {
            CourseEditingScreen()
        }

    }

}

@Composable
private fun CourseEditingScreen(
    name: String? = null,
    modifier: Modifier = Modifier
) {
    val course: Course = if(name == null) Course() else sampleCourse
    val viewModel: CourseEditingViewModel = koinViewModel { parametersOf(course) }
    val courseState by viewModel.courseState.collectAsState()
    CourseEditingLayout(
        course = courseState,
        viewModel = viewModel,
        modifier = modifier,
    )
}

private val sampleCourse = Course(
    name = "SOLID",
    lessons = listOf(
        "SRP",
        "OCP",
        "LSP",
        "ISP",
        "DIP",
    ).mapIndexed { index, lesson -> Lesson(index, lesson) }
)