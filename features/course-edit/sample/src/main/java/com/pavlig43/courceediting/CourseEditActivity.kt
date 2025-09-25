package com.pavlig43.courceediting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.pavlig43.courceediting.ui.theme.Study_checklistTheme
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.compose.koinViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import ru.pavlig.course_edit.CourseEditingLayout
import ru.pavlig.course_edit.CourseEditingViewModel
import ru.pavlig.course_edit.logic.CourseDraftEditor
import ru.pavlig.course_edit.logic.CourseEditInteractor
import ru.pavlig.course_edit.logic.CourseEditState
import ru.pavlig43.core.LoadingLayout

class CourseEditActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            startKoin {
                androidLogger()
                androidContext(application)
                modules(
                    module {
                        factoryOf(::CourseDraftEditor)
                        factoryOf(::CourseEditInteractor)
                        viewModel { (courseId: Int) ->
                            CourseEditingViewModel(
                                courseId = courseId,
                                interactor = get<CourseEditInteractor>()
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
private fun CourseEditScreen(
    modifier: Modifier = Modifier,
) {
    val viewModel: CourseEditingViewModel = koinViewModel { parametersOf(1) }
    val courseState by viewModel.courseState.collectAsState()

    when (courseState) {
        CourseEditState.Loading -> LoadingLayout()
        is CourseEditState.Data ->
            CourseEditingLayout(
                draft = (courseState as CourseEditState.Data).draft,//FIXME
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
}




