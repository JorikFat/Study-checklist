package ru.pavlig43.overview.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import ru.pavlig43.courses_list_impl.ui.CoursesScreen

fun NavGraphBuilder.courses(
    navController: NavHostController
) {
    composable<Destination.Courses> {
        CoursesScreen()
    }
}