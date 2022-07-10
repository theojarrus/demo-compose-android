package com.theost.composeapp.presentation.info

import com.theost.composeapp.domain.data.state.Status
import com.theost.composeapp.domain.data.state.Status.Initial
import com.theost.composeapp.ui.screens.dashboard.model.FilmUi

data class InfoState(
    val status: Status = Initial,
    val data: FilmUi? = null
)
