package com.example.db.presentation.movie_details


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.db.domain.model.Movie
import com.example.db.domain.repository.MovieRepository
import com.example.db.presentation.ui_state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _state = MutableStateFlow<UiState<Movie>>(UiState.Loading)
    val state: StateFlow<UiState<Movie>> = _state

    private var currentMovieId: String? = null

    fun loadMovie(movieId: String) {
        currentMovieId = movieId
        viewModelScope.launch {
            repository.getMovies().map { movies -> movies.firstOrNull { it.id == movieId } }
                .catch { e ->
                    _state.value = UiState.Error(e.message ?: "Failed to load movie")
                }.collect { movie ->
                    if (movie != null) {
                        _state.value = UiState.Success(movie)
                    } else {
                        _state.value = UiState.Error("Movie not found")
                    }
                }
        }
    }

    fun toggleFavorite() {
        val currentState = _state.value
        if (currentState is UiState.Success) {
            viewModelScope.launch {
                try {
                    repository.toggleFavorite(
                        currentState.data.id, !currentState.data.isFavorite
                    )
                } catch (e: Exception) {
                    // Handle error if needed
                }
            }
        }
    }
}