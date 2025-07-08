package com.pavlig43.courceediting.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CourseEditingScreen(
    modifier: Modifier = Modifier) {

    CourseEditingScreen(
        viewModel = viewModel() ,
        modifier = modifier
    )

}
@Composable
private fun CourseEditingScreen(
    viewModel: CourseEditingViewModel,
    modifier: Modifier = Modifier) {
    val courseState by viewModel.courseState.collectAsState()
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        TextField(
            value = courseState.name,
            onValueChange = viewModel::onChangeCourseName,
            placeholder = { Text("Название курса") }
        )
        HorizontalDivider()
        LessonsList(
            lessons = courseState.lessons,
            onChangeLessonName = viewModel::onChangeLessonName
        )
        Button(viewModel::onSave) {
            Text("Сохранить")
        }

    }

}


@Composable
private fun LessonsList(
    lessons:List<Lesson>,
    onChangeLessonName:(index:Int,value:String)->Unit,
    modifier: Modifier = Modifier) {
    val lazyListState = rememberLazyListState()
    LazyColumn(
        modifier = modifier,
        state = lazyListState
    ) {
        items(lessons, key = { it.index}){ lesson->
            TextField(
                value = lesson.name,
                onValueChange = {onChangeLessonName(lesson.index,it)}

            )
        }
    }
}