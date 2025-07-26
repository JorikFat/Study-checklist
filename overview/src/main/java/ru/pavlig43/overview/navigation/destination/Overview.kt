package ru.pavlig43.overview.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import ru.pavlig43.courses_list_impl.ui.CoursesScreen
import ru.pavlig43.overview.ui.OverViewScreen

@Serializable
data object Overview

fun NavGraphBuilder.overview(
    navController: NavHostController
) {
    composable<Overview> {
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
}

