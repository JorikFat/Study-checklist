package com.pavlig43.courceediting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.pavlig43.courceediting.ui.theme.Study_checklistTheme
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.pavlig.course_edit.ui.CourseDraftViewState
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
                        viewModel {
                            CourseEditingViewModel(-1)
                        }
                    }
                )
            }
        }
        enableEdgeToEdge()
        setContent {
            Study_checklistTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val courseState = CourseDraftViewState(
                        name = "SAMPLE Course NAME",
                        lessons = List(5){"Lesson $it"}
                    )
                    CourseEditingLayout(
                        course = courseState,
                        onChangeCourseName = {},
                        onChangeLessonName = {_,_ ->},
                        onSave = {},
                        onCloseScreen = {},
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}




