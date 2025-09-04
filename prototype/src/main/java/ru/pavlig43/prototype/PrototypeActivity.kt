package ru.pavlig43.prototype

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import ru.pavlig43.prototype.screens.OverViewScreen
import ru.pavlig43.prototype.screens.content.ContentScreen
import ru.pavlig43.prototype.screens.courses.CoursesListScreen
import ru.pavlig43.prototype.screens.edit.CourseEditingScreen
import ru.pavlig43.prototype.ui.theme.Study_checklistTheme

class PrototypeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Study_checklistTheme {
                NavigationHost()
            }
        }
    }
}

@Composable
fun NavigationHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Overview
    ) {
        composable<Overview> {
            OverViewScreen(navController)
        }
        composable<Courses> {
            CoursesListScreen(
                onContentScreen = {},
                onAddClick = {}
            )
        }
        composable<Edit> {
            val id = it.toRoute<Edit>().id
            CourseEditingScreen(id, { navController.popBackStack() })
        }
        composable<Content> {
            val id = it.toRoute<Content>().id
            ContentScreen(id)
        }
        composable<Create> {
            CourseEditingScreen(0, { navController.popBackStack() })
        }
    }
}

@Serializable
data object Overview

@Serializable
data object Courses

@Serializable
data class Content(val id: Int)

@Serializable
data class Edit(val id: Int)

@Serializable
data object Create

