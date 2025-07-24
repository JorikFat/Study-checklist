package dev.jorik.study_checklist.course_content.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel


@Composable
fun DisplayingCourseContentScreen(
    modifier: Modifier = Modifier
) {
    val viewModel: DisplayingCourseContentViewModel = koinViewModel()
    val courseState by viewModel.courseState.collectAsState()
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(courseState.name)
        HorizontalDivider()
        LessonsList(
            lessons = courseState.lessons,
            onCheckedChange = viewModel::onCheckedChange
        )

    }
}


@Composable
private fun LessonsList(
    lessons: List<Lesson>,
    onCheckedChange: (index: Int, isChecked: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val lazyListState = rememberLazyListState()
    LazyColumn(
        modifier = modifier,
        state = lazyListState
    ) {
        items(lessons, key = { it.index }) { lesson ->
            LessonRow(
                lesson = lesson,
                onCheckedChange = onCheckedChange
            )
        }
    }
}

@Composable
private fun LessonRow(
    lesson: Lesson,
    onCheckedChange: (index: Int, isChecked: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = lesson.isChecked,
            onCheckedChange = { onCheckedChange(lesson.index, it) }
        )
        Text(lesson.name)
    }

}