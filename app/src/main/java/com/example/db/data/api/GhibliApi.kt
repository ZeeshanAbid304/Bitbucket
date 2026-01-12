package com.example.db.data.api

import retrofit2.http.GET

interface GhibliApi {
    @GET("films")
    suspend fun getFilms(): List<MovieDto>
}