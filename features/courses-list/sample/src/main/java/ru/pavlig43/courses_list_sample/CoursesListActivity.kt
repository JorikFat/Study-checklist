package ru.pavlig43.courses_list_sample

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
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.compose.koinViewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.pavlig43.courses_list_impl.ui.CoursesLayout
import ru.pavlig43.courses_list_impl.ui.CoursesViewModel
import ru.pavlig43.courses_list_sample.ui.theme.Study_checklistTheme

class CoursesListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        if (savedInstanceState == null) startKoin {
            androidLogger()
            androidContext(application)
            modules(
                module {
                    single { CourseInteractor(Course.Stub.courses) }
                    viewModel { CoursesViewModel(get()) }
                }
            )
        }
        setContent {
            Study_checklistTheme {
                CoursesScreen()
            }
        }
    }
}

@Composable
private fun CoursesScreen(
    modifier: Modifier = Modifier,
    onContentScreen: (Int) -> Unit = {},
) {
    val viewModel: CoursesViewModel = koinViewModel()
    val courses by viewModel.courses.collectAsState()

    CoursesLayout(
        courses = courses,
        onContentScreen = { onContentScreen(it.id) },
        onAddButtonClick = {},
        modifier = modifier,
    )
}

