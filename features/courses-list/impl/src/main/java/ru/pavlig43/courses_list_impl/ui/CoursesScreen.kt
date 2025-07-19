package ru.pavlig43.courses_list_impl.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.pavlig43.courses_list_impl.data.Course

@Composable
fun CoursesScreen(modifier: Modifier = Modifier) {
    val viewModel:CoursesViewModel = viewModel()
    val courses by viewModel.courses.collectAsState()

    CoursesScreen(
        courses = courses,
        modifier = modifier
    )

    }

@Composable
private fun CoursesScreen(
    courses: List<Course>,
    modifier: Modifier = Modifier) {
    Column(modifier
        .fillMaxSize()
        .padding(24.dp)) {
        courses.forEach {course->
            CourseCard(
                course = course,
                modifier = Modifier.fillMaxWidth()
            )
        }

    }

}


@Composable
private fun CourseCard(
    course: Course,
    modifier: Modifier = Modifier) {
    OutlinedCard(
        modifier = modifier.padding(16.dp),
        ) {
        Text(course.displayName, modifier = Modifier.padding(16.dp))
    }
}
@Preview(showBackground = true)
@Composable
fun CoursesScreenPreview(modifier: Modifier = Modifier) {
    MaterialTheme {
        CoursesScreen(courseList)
    }

}