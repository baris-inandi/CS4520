package com.itsbaris.cs4520.a7.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.itsbaris.cs4520.a7.ui.detail.BookDetailScreen
import com.itsbaris.cs4520.a7.ui.search.SearchScreen
import com.itsbaris.cs4520.a7.ui.theme.A7_Inandioglu_6696Theme

/**
 * 1. What: Connects the search and detail screens with type-safe navigation.
 * 2. Who:  Called by MainActivity.
 * 3. When: Rendered after the app content is created.
 */
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = BookSearch) {
        composable<BookSearch> {
            SearchScreen(
                onBookClick = { workKey -> navController.navigate(BookDetail(workKey)) },
            )
        }

        composable<BookDetail> { backStackEntry ->
            val route = backStackEntry.toRoute<BookDetail>()
            BookDetailScreen(
                workKey = route.workKey,
                onBack = { navController.popBackStack() },
            )
        }
    }
}

/**
 * 1. What: Previews the navigation host at the start destination.
 * 2. Who:  Used by Android Studio's preview panel.
 * 3. When: Checked while reviewing the app entry flow.
 */
@Preview(showBackground = true)
@Composable
private fun AppNavigationPreview() {
    A7_Inandioglu_6696Theme {
        AppNavigation()
    }
}
