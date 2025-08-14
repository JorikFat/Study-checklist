package ru.pavlig43.prototype.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import dev.jorik.study_checklist.course_content.ui.DisplayingCourseContentScreen
import ru.pavlig.course_edit.ui.CourseEditingLayout
import ru.pavlig.course_edit.ui.CourseEditingViewModel
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
                        is Destination.Content -> navController.navigate(Destination.Content(destination.id))
                        Destination.Courses -> navController.navigate(Destination.Courses)
                        is Destination.Create -> navController.navigate(Destination.Create(destination.id))
                        is Destination.Edit -> navController.navigate(Destination.Edit(destination.id))
                    }
                }
            )
        }
        composable<Destination.Courses> {
            CoursesScreen(
                onEditScreen = {navController.navigate(Destination.Edit(it))},
                onContentScreen = {navController.navigate(Destination.Content(it))}
            )
        }
        composable<Destination.Edit> {
            val id = it.toRoute<Destination.Edit>().id
            CourseEditingScreen(id,{navController.popBackStack()})
        }
        composable<Destination.Content> {
            val id = it.toRoute<Destination.Content>().id
            DisplayingCourseContentScreen(id)
        }
        composable<Destination.Create> {
            CourseEditingScreen(-1,{navController.popBackStack()})
        }

    }

}

@Composable
private fun CourseEditingScreen(
    id:Int,
    onCloseScreen:()->Unit,
    modifier: Modifier = Modifier
) {
    val viewModel = viewModel { CourseEditingViewModel(id) }
    val courseState by viewModel.courseState.collectAsState()
    CourseEditingLayout(
        course = courseState,
        onChangeCourseName = viewModel::onChangeCourseName,
        onChangeLessonName = viewModel::onChangeLessonName,
        onSave = viewModel::onSave,
        onCloseScreen = onCloseScreen,
        modifier = modifier,
    )
}

