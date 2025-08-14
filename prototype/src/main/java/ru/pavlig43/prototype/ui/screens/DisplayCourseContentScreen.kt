package ru.pavlig43.prototype.ui.screens

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
    courseId:Int,
    modifier: Modifier = Modifier,
    viewModel: DisplayingCourseContentViewModel = koinViewModel { parametersOf(courseId) }
) {

    val course by viewModel.courseState.collectAsState()
    DisplayCourseContentLayout(
        course = course,
        toggleLesson = viewModel::toggleLesson,
        modifier = modifier
    )
}