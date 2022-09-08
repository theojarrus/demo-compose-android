package com.theost.composeapp.domain.network

import com.theost.composeapp.domain.data.entity.FilmDto
import com.theost.composeapp.domain.data.entity.response.GetPopularFilmsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkService {

    @GET("v2.2/films/{id}")
    suspend fun getFilm(
        @Path(value = "id")
        id: Int
    ): FilmDto

    @GET("v2.2/films/top")
    suspend fun getPopularFilms(
        @Query(value = "page")
        page: Int
    ): GetPopularFilmsResponse
}
