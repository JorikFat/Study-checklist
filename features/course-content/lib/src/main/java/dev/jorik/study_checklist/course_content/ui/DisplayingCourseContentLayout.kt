package dev.jorik.study_checklist.course_content.ui

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayCourseContentLayout(
    courseContent: CourseContent,
    onEditButtonClick: (CourseContent) -> Unit,
    onBackButtonClick: () -> Unit,
    onCheckedChange: (index: Int, isChecked: Boolean) -> Unit,
    modifier: Modifier = Modifier){

    Scaffold(
        topBar = {
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
                        onClick = { onEditButtonClick(courseContent) }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier.fillMaxSize().padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            LessonsList(
                lessons = courseContent.lessons,
                onCheckedChange = onCheckedChange
            )

        }
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

@Preview(showBackground = true)
@Composable
private fun DisplayCoursePreview() {
    MaterialTheme {
        DisplayCourseContentLayout(
            courseContent = CourseContent(),
            onCheckedChange = { _, _ -> },
            onEditButtonClick = {},
            onBackButtonClick = {}
        )
    }

}