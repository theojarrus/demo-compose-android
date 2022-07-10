package com.theost.composeapp.presentation.dashboard

import com.theost.composeapp.domain.data.state.Status
import com.theost.composeapp.domain.data.state.Status.Initial
import com.theost.composeapp.ui.screens.dashboard.model.FilmUi

data class DashboardState(
    val status: Status = Initial,
    val pagesCount: Int = 1,
    val page: Int = 1,
    val data: List<FilmUi> = emptyList()
)
