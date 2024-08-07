package com.capyreader.app.ui.articles

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.capyreader.app.ui.Route
import com.capyreader.app.ui.articles.search.SearchView

fun NavGraphBuilder.articleGraph(
    navController: NavController,
) {
    composable(
        route = Route.Articles.path,
    ) {
        ArticleScreen(
            onNavigateToSettings = {
                navController.navigate(Route.Settings.path) {
                    launchSingleTop = true
                }
            },
            onNavigateToSearch = {
                navController.navigate(Route.Search.path) {
                    launchSingleTop = true
                }
            }
        )
    }
    composable(route = Route.Search.path) {
        SearchView()
    }
}
