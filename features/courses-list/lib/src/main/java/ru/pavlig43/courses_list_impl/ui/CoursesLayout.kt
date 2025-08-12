package ru.pavlig43.courses_list_impl.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.pavlig43.courses_list_impl.data.Course

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoursesLayout(
    courses: List<Course>,
    onAddButtonClick: () -> Unit,
    onCourseCardClick: (Course) -> Unit,
    onBackButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Список курсов") },
                navigationIcon = {
                    IconButton(
                        onClick = onBackButtonClick
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onAddButtonClick) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "add"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp)
        ) {
            courses.forEach { course ->
                CourseCard(
                    course = course,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onCourseCardClick(course) }
                )
            }

        }
    }
}


@Composable
private fun CourseCard(
    course: Course,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        modifier = modifier.padding(16.dp),
    ) {
        Text(course.displayName, modifier = Modifier.padding(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun CoursesLayoutPreview(modifier: Modifier = Modifier) {
    MaterialTheme {
        CoursesLayout(
            courseList,
            onAddButtonClick = {},
            onCourseCardClick = {},
            onBackButtonClick = {}
        )
    }
}