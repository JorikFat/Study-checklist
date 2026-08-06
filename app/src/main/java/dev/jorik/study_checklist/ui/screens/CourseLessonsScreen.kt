package dev.jorik.study_checklist.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import dev.jorik.study_checklist.course_lessons.ui.CourseLessonsLayout
import dev.jorik.study_checklist.course_lessons.ui.CourseLessonsViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun CourseLessonsScreen(
    id: Int,
    onBackButtonClick: () -> Unit,
    onEditButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel: CourseLessonsViewModel = koinViewModel { parametersOf(id) }
    val courseState by viewModel.courseState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.startJob()
    }
    DisposableEffect(Unit) {
        onDispose { viewModel.stopJob() }
    }

    CourseLessonsLayout(
        course = courseState,
        onEditButtonClick = onEditButtonClick,
        onBackButtonClick = onBackButtonClick,
        toggleLesson = viewModel::toggleLesson,
        modifier = modifier
    )
}