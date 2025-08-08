package ru.pavlig.course_edit.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CourseEditingLayout(
    course: Course,
    onCloseScreen: () -> Unit,
    viewModel: CourseEditingViewModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        TextField(
            value = course.name,
            onValueChange = viewModel::onChangeCourseName,
            placeholder = { Text("Название курса") },
            modifier = Modifier.fillMaxWidth()
        )
        HorizontalDivider()
        LessonsList(
            lessons = course.lessons,
            onChangeLessonName = viewModel::onChangeLessonName,
            modifier = Modifier.fillMaxWidth()
        )
        Button({
            viewModel.onSave()
            onCloseScreen()
        }) {
            Text("Сохранить")
        }

    }

}

@Composable
private fun CourseEditingBody(
    course: Course,
    onChangeCourseName: (String) -> Unit,
    onChangeLessonName: (index: Int, value: String) -> Unit,
    onSave: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        TextField(
            value = course.name,
            onValueChange = onChangeCourseName,
            placeholder = { Text("Название курса") },
            modifier = Modifier.fillMaxWidth()
        )
        HorizontalDivider()
        LessonsList(
            lessons = course.lessons,
            onChangeLessonName = onChangeLessonName,
            modifier = Modifier.fillMaxWidth()
        )
        Button(onSave) {
            Text("Сохранить")
        }

    }
}


@Composable
private fun LessonsList(
    lessons: List<Lesson>,
    onChangeLessonName: (index: Int, value: String) -> Unit,
    modifier: Modifier = Modifier
) {
    val lazyListState = rememberLazyListState()
    LazyColumn(
        modifier = modifier,
        state = lazyListState
    ) {
        items(lessons, key = { it.index }) { lesson ->
            TextField(
                value = lesson.name,
                onValueChange = { onChangeLessonName(lesson.index, it) },
                modifier = Modifier.fillMaxWidth()

            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CourseEditingPreview() {
    MaterialTheme {
        CourseEditingBody(
            course = Course(
                name = "Preview Course",
                lessons = List(3) { Lesson(it, "Preview Lesson $it") }
            ),

            onChangeCourseName = {},
            onChangeLessonName = { _, _ -> },
            onSave = {},
        )
    }

}
