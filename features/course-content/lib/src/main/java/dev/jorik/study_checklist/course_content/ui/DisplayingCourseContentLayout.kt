package dev.jorik.study_checklist.course_content.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DisplayCourseContentLayout(
    course: CourseViewState,
    toggleLesson: (index: Int) -> Unit,
    modifier: Modifier = Modifier){
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(course.name)
        HorizontalDivider()
        LessonsList(
            lessons = course.lessons,
            toggleLesson = toggleLesson
        )

    }
}


@Composable
private fun LessonsList(
    lessons: List<LessonViewState>,
    toggleLesson: (index: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val lazyListState = rememberLazyListState()
    LazyColumn(
        modifier = modifier,
        state = lazyListState
    ) {
        items(lessons.size){index->
            LessonRow(
                lesson = lessons[index],
                toggleLesson = {toggleLesson(index)}
            )
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
            toggleLesson = { _,->}
        )
    }

}