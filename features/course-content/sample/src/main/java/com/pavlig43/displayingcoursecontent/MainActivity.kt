package com.pavlig43.displayingcoursecontent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import dev.jorik.study_checklist.course_content.ui.DisplayingCourseContentScreen
import com.pavlig43.displayingcoursecontent.ui.theme.Study_checklistTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Study_checklistTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DisplayingCourseContentScreen(
                        id = 0,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

