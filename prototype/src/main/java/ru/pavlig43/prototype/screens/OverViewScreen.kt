package ru.pavlig43.prototype.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.pavlig43.prototype.Content
import ru.pavlig43.prototype.Courses
import ru.pavlig43.prototype.Create
import ru.pavlig43.prototype.Edit

@Composable
fun OverViewScreen(
    navController: NavController
) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ScreenButton(title = "Курсы", navigate = { navController.navigate(Courses) })
            ScreenButton(title = "Создание", navigate = { navController.navigate(Create) })
            ScreenButton(title = "Редактирование", navigate = { navController.navigate(Edit(0)) })//TODO: replace to 1
            ScreenButton(title = "Уроки", navigate = { navController.navigate(Content(0)) })//TODO: replace to 1
        }
    }
}

@Composable
private fun ScreenButton(
    title: String,
    navigate: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = { navigate() },
        modifier.fillMaxWidth()
    ) {
        Text(title)
    }
}