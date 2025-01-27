package at.ac.htlleonding.oldtimer_application.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import at.ac.htlleonding.oldtimer_application.ui.components.OldTimerItem
import at.ac.htlleonding.oldtimer_application.ui.viewmodel.OldTimerViewModel

@Composable
fun OldTimerList(
    viewModel: OldTimerViewModel = viewModel(),
    onNavigateMore: () -> Unit,
    onNavigateCreate: () -> Unit
) {
    val oldTimers = viewModel.oldTimers

    LaunchedEffect(Unit) {
        viewModel.getAllOldTimers()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "OldTimer List",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (oldTimers.isEmpty()) {
            Text("No cars available.")
        } else {
            Text(
                text=String.format("%-15s %-15s %-4s", "Marke", "Model", "Year"),
                fontFamily = FontFamily.Monospace,
                modifier = Modifier.padding(8.dp)
            )
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(oldTimers) { oldTimer ->
                    OldTimerItem(oldTimer = oldTimer)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { onNavigateMore() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("More Information")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { onNavigateCreate() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Create new oldtimer")
        }
    }
}