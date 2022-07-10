package com.theost.composeapp.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.theost.composeapp.domain.di.AppComponent
import com.theost.composeapp.ui.di.core.daggerViewModel
import com.theost.composeapp.ui.di.dashboard.DaggerDashboardComponent
import com.theost.composeapp.ui.screens.dashboard.DashboardScreen
import com.theost.composeapp.ui.navigation.ScreenDirection.Dashboard

fun NavGraphBuilder.dashboardRoute(appComponent: AppComponent, navController: NavController) {
    navigation(startDestination = Dashboard.destination, route = Dashboard.route) {
        composable(Dashboard.destination) { backStackEntry ->
            val dashboardComponent = DaggerDashboardComponent.factory().create(appComponent)
            val dashboardViewModel = daggerViewModel { dashboardComponent.getViewModel() }
            DashboardScreen(
                navController = navController,
                backStackEntry = backStackEntry,
                dashboardViewModel = dashboardViewModel
            )
        }
    }
}
