package ru.pavlig43.prototype.ui.screens

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
import ru.pavlig43.prototype.navigation.destination.Destination

@Composable
internal fun OverViewScreen(
    onDestination: (Destination) -> Unit,
) {
    Scaffold() { padding ->
        Column(
            modifier = Modifier.padding(padding)
                .fillMaxSize()
                .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Destination.allDestinations().forEach { destination ->
                DestinationButton(
                    destination = destination,
                    onDestination = onDestination,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }

}

@Composable
private fun DestinationButton(
    destination: Destination,
    onDestination: (Destination) -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = { onDestination(destination) },
        modifier
    ) {
        Text(destination.title)
    }

}