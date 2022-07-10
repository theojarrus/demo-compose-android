package com.theost.composeapp.ui.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.theost.composeapp.domain.di.AppComponent
import com.theost.composeapp.ui.navigation.ScreenDirection.Dashboard
import com.theost.composeapp.ui.navigation.ScreenDirection.Profile
import com.theost.composeapp.ui.navigation.dashboardRoute
import com.theost.composeapp.ui.navigation.profileRoute

@Composable
fun MainScreen(appComponent: AppComponent, navController: NavController) {
    val childNavController = rememberNavController()
    val navItems = listOf(Dashboard, Profile)
    Column {
        Box(modifier = Modifier.weight(1f)) {
            NavHost(navController = childNavController, startDestination = Dashboard.route) {
                dashboardRoute(appComponent, navController)
                profileRoute(appComponent, navController)
            }
        }
        Box(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth()
        ) {
            BottomNavigation {
                val navBackStackEntry by childNavController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                val previousDestination = remember { mutableStateOf(navItems.first().route) }
                navItems.forEach { screen ->
                    val isSelected = currentDestination?.hierarchy
                        ?.any { it.route == screen.route } == true
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                imageVector = screen.iconVector ?: Icons.Filled.Warning,
                                contentDescription = stringResource(screen.titleResId),
                            )
                        },
                        label = { Text(text = stringResource(screen.titleResId)) },
                        selected = isSelected,
                        onClick = {
                            if (screen.route != previousDestination.value) {
                                previousDestination.value = screen.route
                                childNavController.navigate(screen.route) {
                                    popUpTo(childNavController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}
