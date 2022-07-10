package com.theost.composeapp.domain.data.model

data class Film(
    val id: Int,
    val nameRu: String,
    val nameEn: String?,
    val year: String,
    val description: String?,
    val countries: List<String>,
    val genres: List<String>,
    val rating: String,
    val ratingVoteCount: Int?,
    val posterUrl: String
)
