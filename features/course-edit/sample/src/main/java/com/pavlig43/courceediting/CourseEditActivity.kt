package com.pavlig43.courceediting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.courses.CourseInteractor
import com.example.courses.repository.CoursesRepository
import com.example.courses.repository.FakeCoursesRepository
import com.pavlig43.courceediting.ui.theme.Study_checklistTheme
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.compose.koinViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.pavlig.course_edit.CourseEditingLayout
import ru.pavlig.course_edit.CourseEditingViewModel
import ru.pavlig.course_edit.logic.CourseDraftEditor
import ru.pavlig.course_edit.logic.CourseEditInteractor

class CourseEditActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            startKoin {
                androidLogger()
                androidContext(application)
                modules(
                    module {
                        singleOf(::FakeCoursesRepository) bind(CoursesRepository::class)
                        singleOf(::CourseInteractor)
                        factory<CourseDraftEditor> { (id: Int) ->
                            val course = get<CourseInteractor>().findCourseById(id)
                            CourseDraftEditor(course)
                        }
                        factory<CourseEditInteractor> { (id: Int) ->
                            CourseEditInteractor(
                                get<CourseDraftEditor> { parametersOf(id) },
                                get<CoursesRepository>()
                            )
                        }
                        viewModel<CourseEditingViewModel> { (id: Int) ->
                            CourseEditingViewModel(
                                get<CourseEditInteractor> { parametersOf(id) }
                            )
                        }
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
private fun CourseEditScreen() {
    val viewModel: CourseEditingViewModel = koinViewModel { parametersOf(1) }
    val courseState by viewModel.courseState.collectAsState()

    CourseEditingLayout(
        draft = courseState,
        onChangeCourseName = viewModel::onChangeCourseName,
        onChangeLessonName = viewModel::onChangeLessonName,
        onAddLesson = viewModel::onAddLesson,
        onDeleteLesson = viewModel::onDeleteLesson,
        onSave = viewModel::onSave,
        onNavigateBack = {},
        onDeleteCourse = {},
    )
}




