package at.ac.htlleonding.oldtimer_application.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import at.ac.htlleonding.oldtimer_application.data.model.OldTimer

@Composable
fun OldTimerItem(oldTimer: OldTimer){
    Card {
        Text(
            text="${oldTimer.name.padEnd(15)} ${oldTimer.model.padEnd(15)} ${oldTimer.year}",
            fontFamily = FontFamily.Monospace,
            modifier = Modifier.padding(8.dp)
        )
    }
}