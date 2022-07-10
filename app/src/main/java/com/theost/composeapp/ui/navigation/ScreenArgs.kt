package com.theost.composeapp.ui.navigation

import androidx.navigation.NavController
import kotlin.reflect.KProperty1

sealed class ScreenArgs {

    class InfoArgs(navController: NavController, filmId: Int? = null) : ScreenArgs() {
        val filmId: Int? = filmId ?: navController.getIntArgument(InfoArgs::filmId)
    }
}

inline fun <reified T : ScreenArgs> NavController.getIntArgument(field: KProperty1<T, Int?>): Int? {
    return previousBackStackEntry?.savedStateHandle?.get("${T::class.simpleName}=${field.name}")
}

inline fun <reified T : ScreenArgs> NavController.putIntArgument(
    receiver: T,
    field: KProperty1<T, Int?>
) {
    field.get(receiver)?.let { value ->
        currentBackStackEntry?.savedStateHandle?.set("${T::class.simpleName}=${field.name}", value)
    }
}
