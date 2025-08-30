package dev.jorik.study_checklist.course_content.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DisplayCourseContentLayout(
    course: CourseViewState,
    onEditButtonClick: () -> Unit,
    onBackButtonClick: () -> Unit,
    toggleLesson: (index: Int) -> Unit,
    modifier: Modifier = Modifier
) {

    Scaffold(
        topBar = {
            AppBar(
                course,
                onBackButtonClick,
                onEditButtonClick
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            LessonsList(
                lessons = course.lessons,
                toggleLesson = toggleLesson
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar(
    courseContent: CourseViewState,
    onBackButtonClick: () -> Unit,
    onEditButtonClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(courseContent.name)
        },
        navigationIcon = {
            IconButton(onClick = onBackButtonClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null
                )
            }
        },
        actions = {
            IconButton(
                onClick = onEditButtonClick
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = null
                )
            }
        }
    )
}


@Composable
private fun LessonsList(
    lessons: List<LessonViewState>,
    toggleLesson: (index: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(lessons.size) { index ->
            with(lessons[index]) {
                LessonRow(
                    lesson = this,
                    toggleLesson = { toggleLesson(this.index) }
                )
            }

        }
    }
}

@Composable
private fun LessonRow(
    lesson: LessonViewState,
    toggleLesson: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = lesson.isChecked,
            onCheckedChange = { toggleLesson() }
        )
        Text(lesson.name)
    }

}

@Preview(showBackground = true)
@Composable
private fun DisplayCoursePreview() {
    MaterialTheme {
        DisplayCourseContentLayout(
            course = CourseViewState(),
            toggleLesson = {},
            onEditButtonClick = {},
            onBackButtonClick = {},
        )
    }
}