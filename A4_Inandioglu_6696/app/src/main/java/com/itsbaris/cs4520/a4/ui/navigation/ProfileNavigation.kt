package com.itsbaris.cs4520.a4.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.itsbaris.cs4520.a4.model.Profile
import com.itsbaris.cs4520.a4.ui.screens.customize.CustomizeScreen
import com.itsbaris.cs4520.a4.ui.screens.edit.EditInfoScreen
import com.itsbaris.cs4520.a4.ui.screens.home.HomeScreen
import com.itsbaris.cs4520.a4.ui.screens.preview.CompactPreviewScreen
import com.itsbaris.cs4520.a4.ui.screens.preview.FullPreviewScreen
import com.itsbaris.cs4520.a4.ui.screens.preview.ShareablePreviewScreen

@Composable
fun ProfileNavigation() {
    var profile by remember { mutableStateOf(Profile()) }
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = HomeRoute,
    ) {
        composable<HomeRoute> {
            HomeScreen(
                profile = profile,
                onEditInfo = { navController.navigate(EditInfoRoute) },
                onCustomize = { navController.navigate(CustomizeRoute) },
                onCompactPreview = { navController.navigate(CompactPreviewRoute) },
                onFullPreview = { navController.navigate(FullPreviewRoute) },
                onShareablePreview = { navController.navigate(ShareablePreviewRoute) },
            )
        }

        composable<EditInfoRoute> {
            EditInfoScreen(
                initial = profile,
                onSave = { updated ->
                    profile = updated
                    navController.popBackStack()
                },
                onCancel = { navController.popBackStack() },
            )
        }

        composable<CustomizeRoute> {
            CustomizeScreen(
                initial = profile,
                onSave = { updated ->
                    profile = updated
                    navController.popBackStack()
                },
                onCancel = { navController.popBackStack() },
            )
        }

        composable<CompactPreviewRoute> {
            CompactPreviewScreen(
                profile = profile,
                onBack = { navController.popBackStack() },
            )
        }

        composable<FullPreviewRoute> {
            FullPreviewScreen(
                profile = profile,
                onBack = { navController.popBackStack() },
            )
        }

        composable<ShareablePreviewRoute> {
            ShareablePreviewScreen(
                profile = profile,
                onBack = { navController.popBackStack() },
            )
        }
    }
}
