package com.theost.composeapp.domain.data.repository

import com.theost.composeapp.domain.data.entity.ListFilmDto
import com.theost.composeapp.domain.data.mapper.mapToFilm
import com.theost.composeapp.domain.data.model.Film
import com.theost.composeapp.domain.data.model.Node
import com.theost.composeapp.domain.network.NetworkService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class FilmsRepository(
    private val networkService: NetworkService,
    private val dispatcher: CoroutineDispatcher
) {

    suspend fun getFilm(id: Int): Film {
        return withContext(dispatcher) {
            networkService.getFilm(id).mapToFilm()
        }
    }

    suspend fun getPopularFilms(page: Int): Node {
        return withContext(dispatcher) {
            val response = networkService.getPopularFilms(page)
            Node(response.pagesCount, response.films.map(ListFilmDto::mapToFilm))
        }
    }
}
