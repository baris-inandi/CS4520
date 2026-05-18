package com.itsbaris.cs4520.a4.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
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
import com.itsbaris.cs4520.a4.ui.theme.A4_Inandioglu_6696Theme

private const val PREVIEW_ROUTE_TAG = "PreviewRoute"

private val ProfileSaver =
    mapSaver(
        save = { profile: Profile ->
            mapOf(
                "name" to profile.name,
                "bio" to profile.bio,
                "email" to profile.email,
                "level" to profile.level,
                "showOnline" to profile.showOnline,
            )
        },
        restore = { saved ->
            Profile(
                name = saved["name"] as? String ?: "",
                bio = saved["bio"] as? String ?: "",
                email = saved["email"] as? String ?: "",
                level = saved["level"] as? Int ?: 1,
                showOnline = saved["showOnline"] as? Boolean ?: true,
            )
        },
    )

@Composable
fun ProfileNavigation() {
    var profile by rememberSaveable(stateSaver = ProfileSaver) { mutableStateOf(Profile()) }
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
                onCompactPreview = {
                    Log.i(PREVIEW_ROUTE_TAG, "mode=Compact")
                    navController.navigate(CompactPreviewRoute)
                },
                onFullPreview = {
                    Log.i(PREVIEW_ROUTE_TAG, "mode=Full")
                    navController.navigate(FullPreviewRoute)
                },
                onShareablePreview = {
                    Log.i(PREVIEW_ROUTE_TAG, "mode=Share")
                    navController.navigate(ShareablePreviewRoute)
                },
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

@Preview(showBackground = true)
@Composable
private fun ProfileNavigationPreview() {
    A4_Inandioglu_6696Theme {
        ProfileNavigation()
    }
}
