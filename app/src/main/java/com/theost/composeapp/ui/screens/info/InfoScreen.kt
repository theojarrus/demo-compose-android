package com.theost.composeapp.ui.screens.info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.theost.composeapp.R
import com.theost.composeapp.domain.data.state.Status.*
import com.theost.composeapp.presentation.info.InfoViewModel
import com.theost.composeapp.ui.navigation.ScreenArgs.InfoArgs
import com.theost.composeapp.ui.screens.info.views.InfoViewDisplay
import com.theost.composeapp.ui.screens.info.views.InfoViewError
import com.theost.composeapp.ui.screens.info.views.InfoViewLoading

@Composable
fun InfoScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    infoViewModel: InfoViewModel
) {
    Surface(modifier = modifier.fillMaxSize()) {
        val viewState = infoViewModel.state.observeAsState()
        val filmId = InfoArgs(navController).filmId ?: 0
        infoViewModel.loadData(filmId)
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(R.string.info)) },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(Icons.Default.ArrowBack, null)
                        }
                    }
                )
            }, content = { padding ->
                Column(modifier = Modifier.padding(padding)) {
                    when(viewState.value?.status) {
                        is Loading -> InfoViewLoading()
                        is Initial -> InfoViewLoading()
                        is Success -> viewState.value?.let { InfoViewDisplay(it) }
                        else -> InfoViewError()
                    }
                }
            })
    }
}
