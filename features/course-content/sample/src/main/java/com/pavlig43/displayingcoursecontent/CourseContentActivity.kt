package com.pavlig43.displayingcoursecontent

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
import com.example.courses.CourseInteractor
import com.example.courses.models.Course
import com.pavlig43.displayingcoursecontent.ui.theme.Study_checklistTheme
import dev.jorik.study_checklist.course_content.ui.DisplayCourseContentLayout
import dev.jorik.study_checklist.course_content.ui.DisplayingCourseContentViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

class CourseContentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null)
            startKoin {
                androidLogger()
                androidContext(application)
                modules(module {
                    single { CourseInteractor(Course.Stub.courses) }
                    viewModel {(id:Int)-> DisplayingCourseContentViewModel(id, get()) }
                })
            }
        enableEdgeToEdge()
        setContent {
            Study_checklistTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CourseContentScreen(
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}

@Composable
private fun CourseContentScreen(
    modifier: Modifier = Modifier,
) {
    val viewModel: DisplayingCourseContentViewModel = koinViewModel { parametersOf(0) }
    val courseState by viewModel.courseState.collectAsState()

    DisplayCourseContentLayout(
        course = courseState,
        modifier = modifier,
        onEditButtonClick = {},
        onBackButtonClick = {},
        toggleLesson = viewModel::toggleLesson
    )
}

