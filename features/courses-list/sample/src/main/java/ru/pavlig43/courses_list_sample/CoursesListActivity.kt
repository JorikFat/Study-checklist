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
import com.example.courses.repository.CoursesRepository
import com.example.courses.repository.MemoryCoursesRepository
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.compose.koinViewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
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
                    singleOf(::MemoryCoursesRepository) { bind<CoursesRepository>() }
                    singleOf(::CourseInteractor)
                    viewModelOf(::CoursesViewModel)
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
    onEditScreen: (Int) -> Unit = {},
) {
    val viewModel: CoursesViewModel = koinViewModel()
    val courses by viewModel.courses.collectAsState()

    CoursesLayout(
        courses = courses,
        modifier = modifier,
        onAddButtonClick = {},
        onEditScreen = { onEditScreen(it.id) },
        onContentScreen = { onContentScreen(it.id) },
    )
}

