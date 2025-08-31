package ru.pavlig43.prototype.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import ru.pavlig.course_edit.ui.CourseEditingLayout
import ru.pavlig.course_edit.ui.CourseEditingViewModel

@Composable
fun CourseEditingScreen(
    courseId:Int,
    onCloseScreen:()->Unit,
    modifier: Modifier = Modifier
) {

    val viewModel: CourseEditingViewModel = koinViewModel { parametersOf(courseId) }
    val courseState by viewModel.courseState.collectAsState()
    CourseEditingLayout(
        course = courseState,
        onChangeCourseName = viewModel::onChangeCourseName,
        onChangeLessonName = viewModel::onChangeLessonName,
        onAddLesson = viewModel::onAddLesson,
        onDeleteLesson = viewModel::onDeleteLesson,
        onSave = viewModel::onSave,
        onNavigateBack = onCloseScreen,
        onDeleteCourse = {
            viewModel.onDeleteCourse()
            onCloseScreen()
        },

        modifier = modifier,
    )
}

//private val sampleCourse = Course(
//    name = "SOLID",
//    lessons = listOf(
//        "SRP",
//        "OCP",
//        "LSP",
//        "ISP",
//        "DIP",
//    ).mapIndexed { index, lesson -> Lesson(index, lesson) }
//)