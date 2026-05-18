package com.itsbaris.cs4520.a4.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.itsbaris.cs4520.a4.model.Profile
import com.itsbaris.cs4520.a4.ui.theme.A4_Inandioglu_6696Theme

@Composable
fun HomeScreen(
    profile: Profile,
    onEditInfo: () -> Unit,
    onCustomize: () -> Unit,
    onCompactPreview: () -> Unit,
    onFullPreview: () -> Unit,
    onShareablePreview: () -> Unit,
) {
    if (profile.name.isBlank()) {
        IncompleteHomeScreen(onEditInfo = onEditInfo)
    } else {
        CompleteHomeScreen(
            profile = profile,
            onEditInfo = onEditInfo,
            onCustomize = onCustomize,
            onCompactPreview = onCompactPreview,
            onFullPreview = onFullPreview,
            onShareablePreview = onShareablePreview,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenIncompletePreview() {
    A4_Inandioglu_6696Theme {
        HomeScreen(
            profile = Profile(),
            onEditInfo = {},
            onCustomize = {},
            onCompactPreview = {},
            onFullPreview = {},
            onShareablePreview = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenCompletePreview() {
    A4_Inandioglu_6696Theme {
        HomeScreen(
            profile =
                Profile(
                    name = "Baris",
                    bio = "Mobile app development student",
                    email = "baris@example.com",
                    level = 19,
                ),
            onEditInfo = {},
            onCustomize = {},
            onCompactPreview = {},
            onFullPreview = {},
            onShareablePreview = {},
        )
    }
}
