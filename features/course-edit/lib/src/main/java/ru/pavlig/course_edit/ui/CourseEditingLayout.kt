package ru.pavlig.course_edit.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
    viewModel: CourseEditingViewModel,
    modifier: Modifier = Modifier
) {

    CourseEditingBody(
        course = course,
        onChangeCourseName = viewModel::onChangeCourseName,
        onChangeLessonName = viewModel::onChangeLessonName,
        onAddLesson = viewModel::onAddLesson,
        onDeleteLesson = viewModel::onDeleteLesson,
        onSave = viewModel::onSave,
        onNavigateBack = {},
        modifier = modifier
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CourseEditingBody(
    course: Course,
    onChangeCourseName: (String) -> Unit,
    onChangeLessonName: (index: Int, value: String) -> Unit,
    onAddLesson: () -> Unit,
    onDeleteLesson: (Lesson) -> Unit,
    onSave: () -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.padding(vertical = 8.dp),
                title = {
                    TextField(
                        value = course.name,
                        onValueChange = onChangeCourseName,
                        placeholder = { Text("Название курса") },
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateBack
                    ) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, null)
                    }
                },
                actions = {
                    IconButton(
                        onClick = onSave
                    ) {
                        Icon(Icons.Filled.Done, null)
                    }
                }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 8.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            LessonsList(
                lessons = course.lessons,
                onChangeLessonName = onChangeLessonName,
                modifier = Modifier.fillMaxWidth(),
                onDeleteLesson = onDeleteLesson
            )
            Button(onAddLesson) {
                Text("Добавить")
            }

        }

    }
}

@Composable
private fun LessonsList(
    lessons: List<Lesson>,
    onChangeLessonName: (index: Int, value: String) -> Unit,
    onDeleteLesson: (Lesson) -> Unit,
    modifier: Modifier = Modifier
) {
    val lazyListState = rememberLazyListState()
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        state = lazyListState
    ) {
        items(lessons, key = { it.index }) { lesson ->
            LessonItem(
                modifier = Modifier.fillMaxWidth(),
                lesson = lesson,
                onChangeLessonName = onChangeLessonName,
                onDeleteLesson = onDeleteLesson
            )
        }
    }
}

@Composable
private fun LessonItem(
    modifier: Modifier = Modifier,
    lesson: Lesson,
    onChangeLessonName: (index: Int, value: String) -> Unit,
    onDeleteLesson: (Lesson) -> Unit
) {

    Row(modifier) {
        TextField(
            value = lesson.name,
            onValueChange = { onChangeLessonName(lesson.index, it) },
            modifier = Modifier.weight(1f)
        )

        IconButton(
            modifier = Modifier.weight(0.2f),
            onClick = { onDeleteLesson(lesson) }
        ) {
            Icon(
                imageVector = Icons.Default.Clear,
                contentDescription = null
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
            onDeleteLesson = {},
            onAddLesson = {},
            onNavigateBack = {},
            modifier = Modifier,
        )
    }

}
