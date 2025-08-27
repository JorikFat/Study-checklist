package ru.pavlig43.prototype.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import ru.pavlig43.prototype.navigation.destination.Destination
import ru.pavlig43.prototype.navigation.destination.Overview
import ru.pavlig43.prototype.screens.OverViewScreen
import ru.pavlig43.prototype.screens.courses.CoursesListScreen
import ru.pavlig43.prototype.ui.screens.CourseEditingScreen
import ru.pavlig43.prototype.ui.screens.DisplayingCourseContentScreen

@Composable
fun NavigationHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Overview
    ) {
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
            CoursesListScreen(
                onEditScreen = {navController.navigate(Destination.Edit(it))},
                onContentScreen = {navController.navigate(Destination.Content(it))},
                onAddClick = { navController.navigate(Destination.Create()) }
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


