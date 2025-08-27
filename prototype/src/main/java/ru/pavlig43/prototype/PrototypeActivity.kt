package ru.pavlig43.prototype

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ru.pavlig43.prototype.navigation.NavigationHost
import ru.pavlig43.prototype.navigation.destination.Destination
import ru.pavlig43.prototype.ui.theme.Study_checklistTheme

class PrototypeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println(Destination.allDestinations())
        enableEdgeToEdge()
        setContent {
            Study_checklistTheme {
                NavigationHost()
            }
        }
    }
}

