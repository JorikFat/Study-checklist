package dev.jorik.study_checklist.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import dev.jorik.study_checklist.course_content.ui.DisplayCourseContentLayout
import dev.jorik.study_checklist.course_content.ui.DisplayingCourseContentViewModel
import org.koin.androidx.compose.koinViewModel

import org.koin.core.parameter.parametersOf

@Composable
fun DisplayingCourseContentScreen(
    id: Int,
    onBackButtonClick: () -> Unit,
    onEditButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel: DisplayingCourseContentViewModel = koinViewModel { parametersOf(id) }
    val courseState by viewModel.courseState.collectAsState()
    courseState?.let {
        DisplayCourseContentLayout(
            course = courseState!!,
            onEditButtonClick = onEditButtonClick,
            onBackButtonClick = onBackButtonClick,
            toggleLesson = viewModel::toggleLesson,
            modifier = modifier
        )
    }?:Box {}


}