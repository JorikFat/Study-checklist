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
import com.example.courses.CourseInteractor
import com.pavlig43.courceediting.ui.theme.Study_checklistTheme
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.compose.koinViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import ru.pavlig.course_edit.ui.CourseEditingLayout
import ru.pavlig.course_edit.ui.CourseEditingViewModel

class CourseEditActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(savedInstanceState == null){
            startKoin {
                androidLogger()
                androidContext(application)
                modules(
                    module {
                        single { CourseInteractor() }
                        viewModel {(id:Int)->
                            CourseEditingViewModel(id, get())
                        }
                    }
                )
            }
        }
        enableEdgeToEdge()
        setContent {
            Study_checklistTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CourseEditScreen(
                        modifier = Modifier.padding(innerPadding),
                        onNavigateBack = {},
                        id = 0
                    )
                }
            }
        }
    }
}

@Composable
private fun CourseEditScreen(
    modifier: Modifier = Modifier,
    id: Int = 0,
    onNavigateBack :() -> Unit = {}
) {
    val viewModel: CourseEditingViewModel = koinViewModel{parametersOf(id)}
    val courseState by viewModel.courseState.collectAsState()
    CourseEditingLayout(
        course = courseState,
        onChangeCourseName = viewModel::onChangeCourseName,
        onChangeLessonName = viewModel::onChangeLessonName,
        onAddLesson = viewModel::onAddLesson,
        onDeleteLesson = viewModel::onDeleteLesson,
        onSave = viewModel::onSave,
        onNavigateBack = onNavigateBack,
        modifier = modifier,
    )
}




