package ru.pavlig43.prototype.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import dev.jorik.study_checklist.course_content.ui.DisplayCourseContentLayout
import dev.jorik.study_checklist.course_content.ui.DisplayingCourseContentViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DisplayingCourseContentScreen(
    modifier: Modifier = Modifier,
    viewModel: DisplayingCourseContentViewModel = koinViewModel()
) {

    val courseState by viewModel.courseState.collectAsState()
    DisplayCourseContentLayout(
        courseContent = courseState,
        onCheckedChange = viewModel::onCheckedChange,
    )
}