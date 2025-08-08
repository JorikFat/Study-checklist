package ru.pavlig43.courses_list_impl.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.pavlig43.courses_list_impl.data.Course

@Composable
fun CoursesScreen(
    onEditScreen: (Int) -> Unit,
    onContentScreen: (Int) -> Unit,
    modifier: Modifier = Modifier) {
    val viewModel: CoursesViewModel = viewModel()
    val courses by viewModel.courses.collectAsState()

    CoursesScreen(
        courses = courses,
        onEditScreen = onEditScreen,
        onContentScreen=onContentScreen,
        modifier = modifier
    )

}

@Composable
private fun CoursesScreen(
    courses: List<Course>,
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

@Composable
private fun ActionDropMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    onEditScreen:()->Unit,
    onContentScreen:()->Unit,
    modifier: Modifier = Modifier
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        modifier = modifier
    ) {
        CourseDestination.entries.forEach {
            DropdownMenuItem(
                text = {Text(it.title)},
                onClick = {
                    when(it){
                        CourseDestination.Edit -> onEditScreen()
                        CourseDestination.Content -> onContentScreen()
                    }
                }
            )
        }
    }
}

@Composable
private fun CourseCard(
    course: Course,
    onEditScreen: (Int) -> Unit,
    onContentScreen: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded  by remember { mutableStateOf(false) }
    Column(modifier.fillMaxWidth()) {
        OutlinedCard(
            modifier = modifier.padding(16.dp).clickable { expanded = true },
        ) {
            Text(course.displayName, modifier = Modifier.padding(16.dp))
        }
        ActionDropMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            onEditScreen = {onEditScreen(course.id)},
            onContentScreen = {onContentScreen(course.id)},
        )
    }

}

private val courseList =
    listOf("SOLID", "Clean Architecture", "Design Patterns").mapIndexed { id, name ->
        Course(
            id,
            name
        )
    }
private enum class CourseDestination(val title:String){
    Edit("Редактирование"),
    Content("Содержание")
}
@Preview(showBackground = true)
@Composable
fun CoursesScreenPreview(modifier: Modifier = Modifier) {
    MaterialTheme {
        CoursesScreen(
            onContentScreen = {},
            onEditScreen = {},
            courses = courseList,)
    }

}