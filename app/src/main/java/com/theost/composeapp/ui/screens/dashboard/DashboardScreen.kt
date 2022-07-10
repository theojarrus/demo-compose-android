package com.theost.composeapp.ui.screens.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.theost.composeapp.R
import com.theost.composeapp.domain.data.state.Status.Loading
import com.theost.composeapp.domain.data.state.Status.Updating
import com.theost.composeapp.presentation.dashboard.DashboardViewModel
import com.theost.composeapp.ui.navigation.ScreenArgs.InfoArgs
import com.theost.composeapp.ui.navigation.ScreenDirection.Info
import com.theost.composeapp.ui.navigation.putIntArgument
import com.theost.composeapp.ui.screens.dashboard.views.FilmView

@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    backStackEntry: NavBackStackEntry,
    dashboardViewModel: DashboardViewModel
) {
    Surface(modifier.fillMaxSize()) {
        val viewState = dashboardViewModel.state.observeAsState()
        Box(modifier = Modifier.fillMaxHeight()) {
            LazyColumn(modifier = Modifier.wrapContentHeight()) {
                item {
                    Text(
                        text = stringResource(id = R.string.films),
                        fontWeight = Bold,
                        fontSize = 22.sp,
                        modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
                    )
                }
                viewState.value?.data?.let { films ->
                    itemsIndexed(films) { index, film ->
                        FilmView(item = film) { id ->
                            navController.putIntArgument(
                                InfoArgs(navController, id),
                                InfoArgs::filmId
                            )
                            navController.navigate(Info.destination)
                        }
                        if (index == films.size - 5) {
                            SideEffect {
                                dashboardViewModel.loadMoreData()
                            }
                        }
                    }
                    if (viewState.value?.status is Loading || viewState.value?.status is Updating) {
                        item {
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier.padding(
                                        top = 16.dp,
                                        bottom = 16.dp
                                    )
                                )
                            }
                        }
                    } else if (viewState.value?.data?.isNotEmpty() == false) {
                        item {
                            Text(stringResource(R.string.network_error))
                        }
                    }
                }
            }
        }
    }
}
