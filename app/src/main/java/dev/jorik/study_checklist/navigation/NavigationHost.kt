package dev.jorik.study_checklist.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import dev.jorik.study_checklist.navigation.destination.Destination
import dev.jorik.study_checklist.ui.screens.CourseEditingScreen
import dev.jorik.study_checklist.ui.screens.CoursesScreen
import dev.jorik.study_checklist.ui.screens.DisplayingCourseContentScreen

@Composable
fun NavigationHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Destination.Courses
    ) {

        composable<Destination.Courses> {
            CoursesScreen(
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
            DisplayingCourseContentScreen(
                id = id,
                onBackButtonClick = {navController.popBackStack()},
                onEditButtonClick = {navController.navigate(Destination.Edit(id))}
                )
        }
        composable<Destination.Create> {
            CourseEditingScreen(-1,{navController.popBackStack()})
        }
    }
}


