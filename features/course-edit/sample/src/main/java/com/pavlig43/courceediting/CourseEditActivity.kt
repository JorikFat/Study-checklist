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
import com.example.courses.models.Course
import ru.pavlig.course_edit.logic.CourseEditInteractor
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
                        single<CourseInteractor> { CourseInteractor(get<CoursesRepository>()) }
                        factory { (course : Course?) -> CourseDraftEditor(course) }
                        factory { (id: Int) ->
                            val course :Course? = get<CourseInteractor>().findCourseById(id)
                            CourseEditInteractor(
                                get<CourseDraftEditor> { parametersOf(course) },
                                repository = get<CoursesRepository>()
                            )
                        }
                        viewModel { (id: Int) -> CourseEditingViewModel(get { parametersOf(id) }) }
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
        draft = courseState,
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




