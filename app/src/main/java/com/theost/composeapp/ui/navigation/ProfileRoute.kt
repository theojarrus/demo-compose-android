package com.theost.composeapp.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.theost.composeapp.domain.di.AppComponent
import com.theost.composeapp.ui.di.core.daggerViewModel
import com.theost.composeapp.ui.di.profile.DaggerProfileComponent
import com.theost.composeapp.ui.navigation.ScreenDirection.Profile
import com.theost.composeapp.ui.screens.profile.ProfileScreen

fun NavGraphBuilder.profileRoute(appComponent: AppComponent, navController: NavController) {
    navigation(startDestination = Profile.destination, route = Profile.route) {
        composable(Profile.destination) {
            val profileComponent = DaggerProfileComponent.factory().create(appComponent)
            val profileViewModel = daggerViewModel { profileComponent.getViewModel() }
            ProfileScreen(profileViewModel = profileViewModel)
        }
    }
}
