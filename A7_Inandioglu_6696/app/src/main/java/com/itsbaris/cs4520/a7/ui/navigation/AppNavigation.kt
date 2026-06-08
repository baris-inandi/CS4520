package com.itsbaris.cs4520.a7.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.itsbaris.cs4520.a7.ui.detail.BookDetailScreen
import com.itsbaris.cs4520.a7.ui.search.SearchScreen

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
