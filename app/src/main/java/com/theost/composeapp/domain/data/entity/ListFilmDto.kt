package com.theost.composeapp.domain.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class ListFilmDto(
    val filmId: Int = 0,
    val nameOriginal: String = "",
    val nameRu: String = "",
    val nameEn: String? = null,
    val year: String = "",
    val countries: List<CountryDto> = emptyList(),
    val genres: List<GenreDto> = emptyList(),
    val rating: String = "0.0",
    val ratingVoteCount: Int = 0,
    val posterUrl: String = ""
)
