package ru.pavlig43.overview.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import dev.jorik.study_checklist.course_content.ui.DisplayingCourseContentScreen
import kotlinx.serialization.Serializable



fun NavGraphBuilder.content(
    navController: NavHostController
) {
    composable<Destination.Content> {
        DisplayingCourseContentScreen()
    }
}