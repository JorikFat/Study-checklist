package ru.pavlig43.overview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import ru.pavlig43.overview.navigation.NavigationHost
import ru.pavlig43.overview.navigation.destination.Destination
import ru.pavlig43.overview.ui.OverViewScreen
import ru.pavlig43.overview.ui.theme.Study_checklistTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println(Destination.allDestinations())
        enableEdgeToEdge()
        setContent {
            Study_checklistTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavigationHost(modifier = Modifier.fillMaxSize().padding(innerPadding))

                }
            }
        }
    }
}

