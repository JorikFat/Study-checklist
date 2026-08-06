package com.pavlig43.course_lessons

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.courses.CourseInteractor
import com.example.courses.repository.CoursesRepository
import com.example.courses.repository.FakeCoursesRepository
import com.pavlig43.course_lessons.ui.theme.Study_checklistTheme
import dev.jorik.study_checklist.course_lessons.ui.CourseLessonsLayout
import dev.jorik.study_checklist.course_lessons.ui.CourseLessonsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

class CourseLessonsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null)
            startKoin {
                androidLogger()
                androidContext(application)
                modules(module {
                    singleOf(::FakeCoursesRepository) { bind<CoursesRepository>() }
                    singleOf(::CourseInteractor)
                    viewModel { (id: Int) -> CourseLessonsViewModel(id, get()) }
                })
            }
        enableEdgeToEdge()
        setContent {
            Study_checklistTheme {
                CourseLessonsScreen()
            }
        }
    }
}

@Composable
private fun CourseLessonsScreen(
    modifier: Modifier = Modifier,
) {
    val viewModel: CourseLessonsViewModel = koinViewModel { parametersOf(1) }
    val courseState by viewModel.courseState.collectAsState()

    CourseLessonsLayout(
        course = courseState,
        modifier = modifier,
        onEditButtonClick = {},
        onBackButtonClick = {},
        toggleLesson = viewModel::toggleLesson
    )
}

