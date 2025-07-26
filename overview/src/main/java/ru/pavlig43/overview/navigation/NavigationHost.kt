package ru.pavlig43.overview.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ru.pavlig43.overview.navigation.destination.Overview
import ru.pavlig43.overview.navigation.destination.overview

@Composable
fun NavigationHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Overview, modifier = modifier) {
        overview(navController)
    }

}