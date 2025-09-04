package com.pavlig43.courceediting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.courses.CourseInteractor
import com.example.courses.database.repository.CoursesRepository
import com.example.courses.database.repository.FakeCoursesRepository
import com.pavlig43.courceediting.ui.theme.Study_checklistTheme
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.compose.koinViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import ru.pavlig.course_edit.ui.CourseEditingLayout
import ru.pavlig.course_edit.ui.CourseEditingViewModel

class CourseEditActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            startKoin {
                androidLogger()
                androidContext(application)
                modules(
                    module {
                        singleOf(::FakeCoursesRepository) { bind<CoursesRepository>() }
                        singleOf(::CourseInteractor)
                        viewModel { (id: Int) -> CourseEditingViewModel(id, get()) }
                    }
                )
            }
        }
        enableEdgeToEdge()
        setContent {
            Study_checklistTheme {
                CourseEditScreen()
            }
        }
    }
}

@Composable
private fun CourseEditScreen(
    modifier: Modifier = Modifier,
) {
    val viewModel: CourseEditingViewModel = koinViewModel { parametersOf(1) }
    val courseState by viewModel.courseState.collectAsState()
    CourseEditingLayout(
        course = courseState,
        onChangeCourseName = viewModel::onChangeCourseName,
        onChangeLessonName = viewModel::onChangeLessonName,
        onAddLesson = viewModel::onAddLesson,
        onDeleteLesson = viewModel::onDeleteLesson,
        onSave = viewModel::onSave,
        onNavigateBack = {},
        onDeleteCourse = {},
        modifier = modifier,
    )
}




