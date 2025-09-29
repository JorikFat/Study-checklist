package dev.jorik.study_checklist.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import ru.pavlig.course_edit.CourseEditingLayout
import ru.pavlig.course_edit.CourseEditingViewModel
import ru.pavlig43.core.UnsavedChangesDialog

@Composable
fun CourseEditingScreen(
    courseId: Int,
    onContentScreen: () -> Unit,
    onCoursesScreen: () -> Unit,
) {
    var isDialogShow by remember { mutableStateOf(false) }
    val viewModel: CourseEditingViewModel = koinViewModel { parametersOf(courseId) }
    val courseState by viewModel.courseState.collectAsState()

    BackHandler { isDialogShow = true }

    if (isDialogShow) {
        UnsavedChangesDialog(
            onConfirm = onContentScreen,
            onDismissRequest = { isDialogShow = false }
        )
    }

    CourseEditingLayout(
        draft = courseState,
        onChangeCourseName = viewModel::onChangeCourseName,
        onChangeLessonName = viewModel::onChangeLessonName,
        onAddLesson = viewModel::onAddLesson,
        onDeleteLesson = viewModel::onDeleteLesson,
        onSave = {
            viewModel.onSave()
            onContentScreen()
        },
        onNavigateBack = { isDialogShow = true },
        onDeleteCourse = {
            viewModel.onDeleteCourse()
            onCoursesScreen()
        },
    )
}