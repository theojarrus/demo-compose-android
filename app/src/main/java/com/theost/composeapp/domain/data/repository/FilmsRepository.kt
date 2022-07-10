package com.theost.composeapp.domain.data.repository

import com.theost.composeapp.domain.data.entity.FilmDto
import com.theost.composeapp.domain.data.entity.ListFilmDto
import com.theost.composeapp.domain.data.mapper.mapToFilm
import com.theost.composeapp.domain.data.model.Film
import com.theost.composeapp.domain.data.model.Node
import com.theost.composeapp.domain.network.NetworkService
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers.io

class FilmsRepository(private val networkService: NetworkService) {

    fun getFilm(id: Int): Single<Film> {
        return networkService.getFilm(id).map(FilmDto::mapToFilm).subscribeOn(io())
    }

    fun getPopularFilms(page: Int): Single<Node> {
        return networkService.getPopularFilms(page).map { response ->
            Node(response.pagesCount, response.films.map(ListFilmDto::mapToFilm))
        }.subscribeOn(io())
    }
}
