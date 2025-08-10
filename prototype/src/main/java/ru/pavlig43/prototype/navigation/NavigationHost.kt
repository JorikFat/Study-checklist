package ru.pavlig43.prototype.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.pavlig43.prototype.navigation.destination.Destination
import ru.pavlig43.prototype.navigation.destination.Overview
import ru.pavlig43.prototype.ui.screens.CourseEditingScreen
import ru.pavlig43.prototype.ui.screens.CoursesScreen
import ru.pavlig43.prototype.ui.screens.DisplayingCourseContentScreen
import ru.pavlig43.prototype.ui.screens.OverViewScreen

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
            CoursesScreen()
        }
        composable<Destination.Edit> {
            CourseEditingScreen("")
        }
        composable<Destination.Content> {
            DisplayingCourseContentScreen()
        }
        composable<Destination.Create> {
            CourseEditingScreen()
        }

    }

}

