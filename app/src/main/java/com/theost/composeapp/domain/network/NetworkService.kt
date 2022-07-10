package com.theost.composeapp.domain.network

import com.theost.composeapp.domain.data.entity.response.GetPopularFilmsResponse
import com.theost.composeapp.domain.data.entity.response.GetSearchedFilmsResponse
import com.theost.composeapp.domain.data.entity.FilmDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkService {

    @GET("v2.2/films/{id}")
    fun getFilm(
        @Path(value = "id")
        id: Int
    ): Single<FilmDto>

    @GET("v2.2/films/top")
    fun getPopularFilms(
        @Query(value = "page")
        page: Int
    ): Single<GetPopularFilmsResponse>
}
