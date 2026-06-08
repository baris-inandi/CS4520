package com.example.bookfinder.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.bookfinder.ui.detail.BookDetailScreen
import com.example.bookfinder.ui.search.SearchScreen

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
