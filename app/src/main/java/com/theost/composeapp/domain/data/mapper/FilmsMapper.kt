package com.theost.composeapp.domain.data.mapper

import com.theost.composeapp.domain.data.entity.FilmDto
import com.theost.composeapp.domain.data.entity.ListFilmDto
import com.theost.composeapp.domain.data.model.Film

fun FilmDto.mapToFilm(): Film = Film(
    id = kinopoiskId,
    nameRu = nameRu,
    nameEn = nameEn ?: nameOriginal,
    year = year.toString(),
    description = description,
    countries = countries.map { it.country },
    genres = genres.map { it.genre },
    rating = ratingKinopoisk.toString(),
    ratingVoteCount = null,
    posterUrl = posterUrl
)

fun ListFilmDto.mapToFilm(): Film = Film(
    id = filmId,
    nameRu = nameRu,
    nameEn = nameEn ?: nameOriginal,
    year = year,
    description = null,
    countries = countries.map { it.country },
    genres = genres.map { it.genre },
    rating = rating,
    ratingVoteCount = ratingVoteCount,
    posterUrl = posterUrl
)
