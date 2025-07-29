package ru.pavlig43.overview.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.jorik.study_checklist.course_content.ui.DisplayingCourseContentScreen
import ru.pavlig.course_edit.ui.CourseEditingScreen
import ru.pavlig43.courses_list_impl.ui.CoursesScreen
import ru.pavlig43.overview.navigation.destination.Destination
import ru.pavlig43.overview.navigation.destination.Overview
import ru.pavlig43.overview.ui.OverViewScreen

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
                        Destination.Create -> navController.navigate(Destination.Edit)
                        Destination.Edit -> navController.navigate(Destination.Edit)
                    }
                }
            )
        }
        composable<Destination.Courses> {
            CoursesScreen()
        }
        composable<Destination.Edit> {
            CourseEditingScreen()
        }
        composable<Destination.Content> {
            DisplayingCourseContentScreen()
        }

    }

}