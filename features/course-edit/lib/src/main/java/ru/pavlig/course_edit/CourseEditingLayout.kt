package ru.pavlig.course_edit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.pavlig.course_edit.logic.models.CourseDraft
import ru.pavlig.course_edit.logic.models.LessonDraft

@Composable
fun CourseEditingLayout(
    draft: CourseDraft,
    onChangeCourseName: (String) -> Unit,
    onChangeLessonName: (index: Int, value: String) -> Unit,
    onAddLesson: () -> Unit,
    onDeleteLesson: (index: Int) -> Unit,
    onSave: () -> Unit,
    onDeleteCourse: () -> Unit,
    onNavigateBack: () -> Unit,
) {

    Scaffold(
        topBar = {
            AppBar(
                draft = draft,
                onChangeCourseName = onChangeCourseName,
                onNavigateBack =onNavigateBack,
                onSave = onSave
            )
        },
        floatingActionButton = {
            IconButton(
                onDeleteCourse,
                colors = IconButtonColors(
                    containerColor = Color.Red,
                    contentColor = Color.White,
                    disabledContainerColor = Color.Red,
                    disabledContentColor = Color.White
                )
            ) {
                Icon(Icons.Default.Delete, "delete")
            }
        },
        floatingActionButtonPosition = FabPosition.Start
    ) { paddingValues ->

            LessonsList(
                lessons = draft.lessons.map { it.name },
                onChangeLessonName = onChangeLessonName,
                onAddLesson = onAddLesson,
                onDeleteLesson = onDeleteLesson,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 8.dp, vertical = 8.dp),
            )

        }
    }


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar(
    draft: CourseDraft,
    onChangeCourseName: (String) -> Unit,
    onNavigateBack: () -> Unit,
    onSave: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            TextField(
                value = draft.name,
                onValueChange = {value->onChangeCourseName(value.replaceFirstChar { it.titlecase() })},
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


@Composable
private fun LessonsList(
    lessons: List<String>,
    onAddLesson: () -> Unit,
    onChangeLessonName: (index: Int, value: String) -> Unit,
    onDeleteLesson: (index: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val lazyListState = rememberLazyListState()
    var previousSize by remember { mutableIntStateOf(lessons.size) }
    LaunchedEffect(lessons.size) {
        if (lessons.size > previousSize) {
            lazyListState.animateScrollToItem(lessons.size)
        }
        previousSize = lessons.size
    }
    
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        state = lazyListState,
    ) {
        itemsIndexed(lessons) { index, lesson ->
            LessonItem(
                modifier = Modifier.fillMaxWidth(),
                lessonName = lesson,
                onChangeLessonName = { value -> onChangeLessonName(index, value) },
                onDeleteLesson = { onDeleteLesson(index) }
            )
        }
        item {
            Button(onAddLesson) {
                Text("Добавить")
            }
        }

    }
}

@Composable
private fun LessonItem(
    lessonName: String,
    onChangeLessonName: (value: String) -> Unit,
    onDeleteLesson: () -> Unit,
    modifier: Modifier = Modifier
) {

    Row(modifier) {
        TextField(
            value = lessonName,
            onValueChange = {value-> onChangeLessonName(value.replaceFirstChar { it.titlecase() }) },
            modifier = Modifier.weight(1f)
        )
        IconButton(
            modifier = Modifier.weight(0.2f),
            onClick = onDeleteLesson
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

        CourseEditingLayout(
            draft = CourseDraft(
                name = "Preview Course",
                lessons = List(3) { LessonDraft(it, "Preview Lesson $it", false) }
            ),

            onChangeCourseName = {},
            onChangeLessonName = { _, _ -> },
            onSave = {},
            onDeleteLesson = {},
            onAddLesson = {},
            onNavigateBack = {},
            onDeleteCourse = {},
        )
    }
}


