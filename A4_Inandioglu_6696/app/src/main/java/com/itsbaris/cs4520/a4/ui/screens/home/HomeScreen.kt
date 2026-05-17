package com.itsbaris.cs4520.a4.ui.screens.home

import androidx.compose.runtime.Composable
import com.itsbaris.cs4520.a4.model.Profile

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
