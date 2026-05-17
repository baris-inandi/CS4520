package com.itsbaris.cs4520.a4.ui.profiledashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.itsbaris.cs4520.a4.ui.theme.A4_Inandioglu_6696Theme

@Composable
internal fun UserSettingsSection(
    notificationsEnabled: Boolean,
    onNotificationsChange: (Boolean) -> Unit,
    level: Int,
    onDecreaseLevel: () -> Unit,
    onIncreaseLevel: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text("Receive Notifications")
            Switch(
                checked = notificationsEnabled,
                onCheckedChange = onNotificationsChange,
            )
        }

        LevelCounter(
            level = level,
            onDecrease = onDecreaseLevel,
            onIncrease = onIncreaseLevel,
        )
    }
}

@Composable
private fun LevelCounter(
    level: Int,
    onDecrease: () -> Unit,
    onIncrease: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Button(
            onClick = onDecrease,
            enabled = level > 1,
        ) {
            Text("-")
        }

        Text(
            text = "Level $level",
            style = MaterialTheme.typography.titleMedium,
        )

        Button(
            onClick = onIncrease,
            enabled = level < 100,
        ) {
            Text("+")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun UserSettingsSectionPreview() {
    A4_Inandioglu_6696Theme {
        UserSettingsSection(
            notificationsEnabled = true,
            onNotificationsChange = {},
            level = 42,
            onDecreaseLevel = {},
            onIncreaseLevel = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LevelCounterPreview() {
    A4_Inandioglu_6696Theme {
        LevelCounter(
            level = 42,
            onDecrease = {},
            onIncrease = {},
        )
    }
}
