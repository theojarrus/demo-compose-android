package com.theost.composeapp.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.theost.composeapp.domain.di.AppComponent
import com.theost.composeapp.ui.di.core.daggerViewModel
import com.theost.composeapp.ui.di.info.DaggerInfoComponent
import com.theost.composeapp.ui.navigation.ScreenDirection.Info
import com.theost.composeapp.ui.navigation.ScreenDirection.Main
import com.theost.composeapp.ui.screens.info.InfoScreen
import com.theost.composeapp.ui.screens.main.MainScreen

fun NavGraphBuilder.mainRoute(appComponent: AppComponent, navController: NavController) {
    composable(Main.destination) {
        MainScreen(appComponent, navController)
    }
    composable(Info.destination) {
        val infoComponent = DaggerInfoComponent.factory().create(appComponent)
        val infoViewModel = daggerViewModel { infoComponent.getViewModel() }
        InfoScreen(navController = navController, infoViewModel = infoViewModel)
    }
}
