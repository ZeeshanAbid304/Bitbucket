package com.example.db.domain.repository

import com.example.db.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(): Flow<List<Movie>>
    suspend fun refresh()
    suspend fun toggleFavorite(id: String, fav: Boolean)
}