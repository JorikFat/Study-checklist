package dev.jorik.study_checklist.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import dev.jorik.study_checklist.navigation.destination.Destination
import dev.jorik.study_checklist.ui.screens.CourseEditingScreen
import dev.jorik.study_checklist.ui.screens.CourseLessonsScreen
import dev.jorik.study_checklist.ui.screens.CoursesScreen

@Composable
fun NavigationHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Destination.Courses
    ) {

        composable<Destination.Courses> {
            CoursesScreen(
                onSelectCourse = { navController.navigate(Destination.Lessons(it)) },
                onAddClick = { navController.navigate(Destination.Create()) }
            )
        }
        composable<Destination.Edit> {
            val id = it.toRoute<Destination.Edit>().id
            CourseEditingScreen(
                courseId = id,
                onCoursesScreen = {
                    navController.navigate(Destination.Courses) {
                        popUpTo(0) { inclusive = true }
                        launchSingleTop = true
                    }
                },
                onContentScreen = { navController.popBackStack() },
            )
        }
        composable<Destination.Lessons> {
            val id = it.toRoute<Destination.Lessons>().id
            CourseLessonsScreen(
                id = id,
                onBackButtonClick = { navController.popBackStack() },
                onEditButtonClick = { navController.navigate(Destination.Edit(id)) }
            )
        }
        composable<Destination.Create> {
            CourseEditingScreen(
                courseId = 0,
                onCoursesScreen = {
                    navController.navigate(Destination.Courses) {
                        popUpTo(0) { inclusive = true }
                        launchSingleTop = true
                    }
                },
                onContentScreen = { navController.popBackStack() },
            )
        }
    }
}


