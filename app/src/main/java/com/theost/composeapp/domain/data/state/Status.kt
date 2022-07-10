package com.theost.composeapp.domain.data.state

sealed class Status {

    object Initial : Status()
    object Loading : Status()
    object Updating : Status()
    object Loaded : Status()
    object Success : Status()
    class Error(val cause: Throwable) : Status()
}
