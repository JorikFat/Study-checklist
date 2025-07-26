package ru.pavlig43.overview.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import ru.pavlig.course_edit.ui.Course
import ru.pavlig.course_edit.ui.CourseEditingScreen


@Serializable
data object CourseEdit

fun NavGraphBuilder.courseEdit(
    navController: NavHostController
) {
    composable<CourseEdit> {
        CourseEditingScreen()
    }
}
