package ru.pavlig43.courses_list_impl.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.pavlig43.courses_list_impl.data.CourseItemViewState


@Composable
 fun CoursesLayout(
    courses: List<CourseItemViewState>,
    onEditScreen: (Int) -> Unit,
    onContentScreen: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        courses.forEach { course ->
            CourseCard(
                course = course,
                onEditScreen = onEditScreen,
                onContentScreen = onContentScreen,
                modifier = Modifier.fillMaxWidth()
            )
        }

    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun CourseCard(
    course: CourseItemViewState,
    onEditScreen: (Int) -> Unit,
    onContentScreen: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier.fillMaxWidth()) {
        OutlinedCard(
            modifier = modifier.padding(16.dp).combinedClickable(
                onClick = {onContentScreen(course.id)},
                onLongClick = {onEditScreen(course.id)}
            ),
        ) {
            Text(course.displayName, modifier = Modifier.padding(16.dp))
        }

    }

}

private val courseList =
    listOf("SOLID", "Clean Architecture", "Design Patterns").mapIndexed { id, name ->
        CourseItemViewState(
            id,
            name
        )
    }

@Preview(showBackground = true)
@Composable
fun CoursesLayoutPreview(modifier: Modifier = Modifier) {
    MaterialTheme {
        CoursesLayout(
            onContentScreen = {},
            onEditScreen = {},
            courses = courseList,)
    }

}