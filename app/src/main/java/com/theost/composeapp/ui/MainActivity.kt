package com.theost.composeapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.theost.composeapp.domain.di.AppComponent
import com.theost.composeapp.domain.di.DaggerAppComponent
import com.theost.composeapp.ui.navigation.ScreenDirection.Main
import com.theost.composeapp.ui.navigation.mainRoute
import com.theost.composeapp.ui.theme.ComposeAppTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var appComponent: AppComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent = DaggerAppComponent.create().apply { inject(this@MainActivity) }
        setContent {
            ComposeAppTheme {
                val navController = rememberNavController()
                Surface {
                    NavHost(
                        navController = navController,
                        startDestination = Main.destination,
                        route = Main.route
                    ) {
                        mainRoute(appComponent, navController)
                    }
                }
            }
        }
    }
}
