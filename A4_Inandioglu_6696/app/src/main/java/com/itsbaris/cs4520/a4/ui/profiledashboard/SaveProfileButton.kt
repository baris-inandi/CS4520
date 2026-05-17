package com.itsbaris.cs4520.a4.ui.profiledashboard

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.itsbaris.cs4520.a4.ui.theme.A4_Inandioglu_6696Theme

@Composable
internal fun SaveProfileButton(
    enabled: Boolean,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text("Save Profile")
    }
}

@Preview(showBackground = true)
@Composable
private fun SaveProfileButtonPreview() {
    A4_Inandioglu_6696Theme {
        SaveProfileButton(
            enabled = true,
            onClick = {},
        )
    }
}
