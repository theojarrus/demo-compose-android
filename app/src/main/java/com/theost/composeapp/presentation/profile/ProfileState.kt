package com.theost.composeapp.presentation.profile

import com.theost.composeapp.domain.data.model.User
import com.theost.composeapp.domain.data.state.Status
import com.theost.composeapp.domain.data.state.Status.Initial

data class ProfileState(
    val status: Status = Initial,
    val data: User? = null
)
