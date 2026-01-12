package com.example.db.data.repository


import com.example.db.data.api.GhibliApi
import com.example.db.data.db.MovieDao
import com.example.db.data.mapper.toDomain
import com.example.db.data.mapper.toEntity
import com.example.db.domain.model.Movie
import com.example.db.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val api: GhibliApi,
    private val dao: MovieDao
) : MovieRepository {

    override fun getMovies(): Flow<List<Movie>> {
        return dao.getMovies().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun refresh() {
        try {
            val movies = api.getFilms()
            val entities = movies.map { it.toEntity() }
            dao.insertAll(entities)
        } catch (e: Exception) {
            // If there's no cached data, throw the exception
            throw e
        }
    }

    override suspend fun toggleFavorite(id: String, fav: Boolean) {
        dao.updateFavorite(id, fav)
    }
}