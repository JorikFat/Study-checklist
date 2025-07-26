package ru.pavlig43.overview.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import ru.pavlig.course_edit.ui.CourseEditingScreen



fun NavGraphBuilder.courseEdit(
    navController: NavHostController
) {
    composable<Destination.Edit> {
        CourseEditingScreen()
    }
}
