package ru.pavlig43.prototype.screens.courses

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel
import ru.pavlig43.courses_list_impl.ui.CoursesLayout
import ru.pavlig43.courses_list_impl.ui.CoursesViewModel

@Composable
fun CoursesListScreen(
    onEditScreen: (Int) -> Unit,
    onContentScreen: (Int) -> Unit,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel :CoursesViewModel = koinViewModel()
    val courses by viewModel.courses.collectAsState()

    CoursesLayout(
        courses = courses,
        modifier = modifier,
        onAddButtonClick = onAddClick,
        onContentScreen = { onContentScreen(it.id) },
        onEditScreen = { onEditScreen(it.id) },
    )
}