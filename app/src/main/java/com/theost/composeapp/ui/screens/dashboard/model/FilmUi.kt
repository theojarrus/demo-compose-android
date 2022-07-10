package com.theost.composeapp.ui.screens.dashboard.model

import com.theost.composeapp.domain.data.model.Film

data class FilmUi(
    val id: Int,
    val name: String,
    val year: String,
    val description: String,
    val countries: String,
    val genres: String,
    val rating: String,
    val posterUrl: String,
    val isFavourite: Boolean
)

fun Film.mapToFilmUi(favourites: List<Int> = emptyList()): FilmUi {
    return FilmUi(
        id = id,
        name = nameRu + nameEn?.run { " ($nameEn)" },
        year = year,
        description = description.orEmpty(),
        countries = countries.joinToString(", "),
        genres = genres.joinToString(", "),
        rating = rating + ratingVoteCount?.run { " ($ratingVoteCount)" },
        posterUrl = posterUrl,
        isFavourite = favourites.contains(id)
    )
}
