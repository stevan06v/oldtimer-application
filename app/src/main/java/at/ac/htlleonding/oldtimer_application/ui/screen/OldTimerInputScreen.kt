package at.ac.htlleonding.oldtimer_application.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import at.ac.htlleonding.oldtimer_application.data.model.OldTimer
import at.ac.htlleonding.oldtimer_application.ui.viewmodel.OldTimerViewModel
import java.util.Calendar

@Composable
fun OldTimerInputScreen(
    oldTimerViewModel: OldTimerViewModel = viewModel(),
    onNavigateToList: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var model by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Add a New Old Timer",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )


        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Car Name") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = model,
            onValueChange = { model = it },
            label = { Text("Car Model") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = year,
            onValueChange = { year = it },
            label = { Text("Car Build Year") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        if (errorMessage != null) {
            Text(
                text = errorMessage!!,
                color = Color.Red,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Button(
            onClick = {
                val parsedYear = year.toIntOrNull()
                val currentYear = Calendar.getInstance().get(Calendar.YEAR)

                when {
                    name.isBlank() -> errorMessage = "Car name cannot be empty."
                    model.isBlank() -> errorMessage = "Car model cannot be empty."
                    parsedYear == null -> errorMessage = "Invalid year format."
                    currentYear - parsedYear < 30 -> errorMessage = "Car must be at least 30 years old."
                    else -> {
                        oldTimerViewModel.addOldTimer(
                            OldTimer(
                                name = name,
                                model = model,
                                year = parsedYear
                            )
                        )
                        onNavigateToList()
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            // ai-magic
            enabled = year.toIntOrNull()?.let { parsedYear ->
                Calendar.getInstance().get(Calendar.YEAR) - parsedYear >= 30
            } ?: false

        ) {
            Text("Add Old Timer")
        }

        Button(
            onClick = {onNavigateToList()},
            modifier = Modifier
                .fillMaxWidth()

        ){
            Text("Exit")
        }
    }
}