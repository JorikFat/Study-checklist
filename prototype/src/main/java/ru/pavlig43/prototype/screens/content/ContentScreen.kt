package ru.pavlig43.prototype.screens.content

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import dev.jorik.study_checklist.course_content.ui.DisplayCourseContentLayout
import dev.jorik.study_checklist.course_content.ui.DisplayingCourseContentViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun ContentScreen(
    id :Int,
    modifier: Modifier = Modifier
) {
    val viewModel: DisplayingCourseContentViewModel = koinViewModel { parametersOf(id) }
    val courseState by viewModel.courseState.collectAsState()

    DisplayCourseContentLayout(
        course = courseState,
        onEditButtonClick = {},
        onBackButtonClick = {},
        toggleLesson = viewModel::toggleLesson,
        modifier = modifier
    )
}