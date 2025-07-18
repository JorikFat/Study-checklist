package ru.pavlig43.overview.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.pavlig43.overview.navigation.Destination

@Composable
fun OverViewScreen(
    onDestination: (Destination) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
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

@Composable
fun DestinationButton(
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