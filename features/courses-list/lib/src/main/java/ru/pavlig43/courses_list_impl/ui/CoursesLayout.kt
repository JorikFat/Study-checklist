package ru.pavlig43.courses_list_impl.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.pavlig43.courses_list_impl.data.CourseItemViewState

//TODO: rename to "CoursesListLayout"
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoursesLayout(
    courses: List<CourseItemViewState>,
    onContentScreen: (CourseItemViewState) -> Unit,
    onAddButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Список курсов") }
            )
        },
        floatingActionButton = {
            IconButton(
                onClick = onAddButtonClick,
                colors = IconButtonColors(
                    containerColor = Color.Blue,
                    contentColor = Color.White,
                    disabledContainerColor = Color.Blue,
                    disabledContentColor = Color.White
                )
            ) {
                Icon(Icons.Default.Add, "")
            }
        }
    ) { paddingValues ->
        Column(
            modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            courses.forEach { course ->
                CourseCard(
                    course = course,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onContentScreen(course) }
                )
            }
        }
    }
}


//@Composable
//fun CoursesLayout(
//    courses: List<CourseItemViewState>,
//    onEditScreen: (Int) -> Unit,
//    onContentScreen: (Int) -> Unit,
//    modifier: Modifier = Modifier
//) {
//    Column(
//        modifier
//            .fillMaxSize()
//            .padding(24.dp)
//    ) {
//        courses.forEach { course ->
//            CourseCard(
//                course = course,
//                onEditScreen = onEditScreen,
//                onContentScreen = onContentScreen,
//                modifier = Modifier.fillMaxWidth()
//            )
//        }
//    }
//}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun CourseCard(
    course: CourseItemViewState,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        modifier = modifier.padding(16.dp),
    ) {
        Text(course.displayName, modifier = Modifier.padding(16.dp))
    }
//    course: CourseItemViewState,
//    onEditScreen: (Int) -> Unit,
//    onContentScreen: (Int) -> Unit,
//    modifier: Modifier = Modifier
//)
//        {
//    Column(modifier.fillMaxWidth()) {
//        OutlinedCard(
//            modifier = modifier.padding(16.dp).combinedClickable(
//                onClick = {onContentScreen(course.id)},
//                onLongClick = {onEditScreen(course.id)}
//            ),
//        ) {
//            Text(course.displayName, modifier = Modifier.padding(16.dp))
//        }
//
//    }
//
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
//        CoursesLayout(
//            courseList,
//            onCourseCardClick = {},
//            onCourseCardLongClick = {}
//        )
        CoursesLayout(
            onContentScreen = {},
            onAddButtonClick = {},
            courses = courseList,
        )
    }

}