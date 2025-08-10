package ru.pavlig43.courses_list_sample.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel
import ru.pavlig43.courses_list_impl.ui.CoursesLayout
import ru.pavlig43.courses_list_impl.ui.CoursesViewModel

@Composable
fun CoursesScreen(
    modifier: Modifier = Modifier,
    viewModel: CoursesViewModel = koinViewModel()
) {

    val courses by viewModel.courses.collectAsState()

    CoursesLayout(
        courses = courses,
        modifier = modifier
    )

}