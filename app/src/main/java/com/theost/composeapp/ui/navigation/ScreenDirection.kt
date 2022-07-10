package com.theost.composeapp.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.theost.composeapp.R

sealed class ScreenDirection(
    val destination: String,
    val route: String,
    @StringRes val titleResId: Int,
    val iconVector: ImageVector? = null
) {

    object Main : ScreenDirection(
        destination = "main",
        route = "main_route",
        titleResId = R.string.app_name
    )

    object Dashboard : ScreenDirection(
        destination = "dashboard",
        route = "dashboard_route",
        titleResId = R.string.dashboard,
        iconVector = Icons.Filled.Home
    )

    object Profile : ScreenDirection(
        destination = "profile",
        route = "profile_route",
        titleResId = R.string.profile,
        iconVector = Icons.Filled.Person
    )

    object Info : ScreenDirection(
        destination = "info",
        route = "info_route",
        titleResId = R.string.info
    )
}
