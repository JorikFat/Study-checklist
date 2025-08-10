package com.pavlig43.courceediting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.pavlig43.courceediting.ui.CourseEditingScreen
import com.pavlig43.courceediting.ui.theme.Study_checklistTheme

class CourseEditActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Study_checklistTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CourseEditingScreen(
//                        name = null,
                        name = "",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}




