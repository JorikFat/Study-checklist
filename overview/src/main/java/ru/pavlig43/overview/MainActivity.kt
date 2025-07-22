package ru.pavlig43.overview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.KoinAndroidContext
import ru.pavlig43.overview.ui.OverViewScreen
import ru.pavlig43.overview.ui.theme.Study_checklistTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Study_checklistTheme {
                KoinAndroidContext {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        OverViewScreen(
                            onDestination = {},
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }

            }
        }
    }
}

