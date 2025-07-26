package com.pavlig43.courceediting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pavlig43.courceediting.ui.theme.Study_checklistTheme
import ru.pavlig.course_edit.ui.Course
import ru.pavlig.course_edit.ui.CourseEditingLayout
import ru.pavlig.course_edit.ui.CourseEditingViewModel
import ru.pavlig.course_edit.ui.Lesson

class CourseEditActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Study_checklistTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CourseEditingScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
private fun CourseEditingScreen(
    modifier: Modifier = Modifier
) {
//    val course: Course = sampleCourse
    val course: Course = Course()
    val viewModel = viewModel { CourseEditingViewModel(course) }
    val courseState by viewModel.courseState.collectAsState()
    CourseEditingLayout(
        course = courseState,
        viewModel = viewModel,
        modifier = modifier,
    )
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


