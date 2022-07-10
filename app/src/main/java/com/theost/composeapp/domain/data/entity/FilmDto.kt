package com.theost.composeapp.domain.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class FilmDto(
    val kinopoiskId: Int = 0,
    val nameOriginal: String = "",
    val nameRu: String = "",
    val nameEn: String? = null,
    val year: Int = 0,
    val description: String = "",
    val countries: List<CountryDto> = emptyList(),
    val genres: List<GenreDto> = emptyList(),
    val ratingKinopoisk: Float = 0f,
    val posterUrl: String = "",
)
