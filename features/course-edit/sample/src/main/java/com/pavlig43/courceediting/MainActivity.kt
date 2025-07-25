package com.pavlig43.courceediting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import ru.pavlig.course_edit.ui.CourseEditingScreen
import com.pavlig43.courceediting.ui.theme.Study_checklistTheme
import ru.pavlig.course_edit.ui.Course
import ru.pavlig.course_edit.ui.Lesson

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Study_checklistTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CourseEditingScreen(
                        course = sampleCourse,
                        modifier = Modifier.padding(
                            innerPadding
                        )
                    )
                }
            }
        }
    }
}

private val sampleCourse = Course(
    name = "SOLID",
    lessons = listOf(
        "SRP",
        "OCP",
        "LSP",
        "ISP",
        "DIP",
    ).mapIndexed { index, lesson -> Lesson(index, lesson) }
)


