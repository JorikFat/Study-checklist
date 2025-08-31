package ru.pavlig43.prototype.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import ru.pavlig.course_edit.ui.CourseEditingLayout
import ru.pavlig.course_edit.ui.CourseEditingViewModel

@Composable
fun CourseEditingScreen(
    courseId: Int,
    onCloseScreen: () -> Unit,
    modifier: Modifier = Modifier
) {

    var isDialogShow by remember { mutableStateOf(false) }
    val viewModel: CourseEditingViewModel = koinViewModel { parametersOf(courseId) }
    val courseState by viewModel.courseState.collectAsState()
    BackHandler { isDialogShow = true }
    if (isDialogShow) {
        UnsavedChangesDialog(
            onConfirm = onCloseScreen,
            onDismissRequest = { isDialogShow = false }
        )
    }
    CourseEditingLayout(
        course = courseState,
        onChangeCourseName = viewModel::onChangeCourseName,
        onChangeLessonName = viewModel::onChangeLessonName,
        onAddLesson = viewModel::onAddLesson,
        onDeleteLesson = viewModel::onDeleteLesson,
        onSave = viewModel::onSave,
        showDialog = { isDialogShow = true },
        modifier = modifier,
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UnsavedChangesDialog(
    onConfirm: () -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {

    BasicAlertDialog(onDismissRequest, modifier) {
        Surface(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight(),
            shape = MaterialTheme.shapes.large,
            tonalElevation = AlertDialogDefaults.TonalElevation,
        ) {
            Column(Modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Выйти без сохранения?")
                Spacer(Modifier.height(16.dp))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    Button(onClick = onConfirm, Modifier.weight(1f)) {
                        Text("Да")
                    }
                    Button(onClick = onDismissRequest, Modifier.weight(1f)) {
                        Text("Остаться")
                    }
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun DialogPreview() {
    MaterialTheme {
        UnsavedChangesDialog(
            onConfirm = {},
            onDismissRequest = {}
        )
    }

}