package com.example.db.presentation.movie_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.db.domain.model.Movie
import com.example.db.domain.repository.MovieRepository
import com.example.db.presentation.ui_state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject


enum class FilterType {
    ALL, FAVORITES
}

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _state = MutableStateFlow<UiState<List<Movie>>>(UiState.Loading)
    val state: StateFlow<UiState<List<Movie>>> = _state

    private val _filterType = MutableStateFlow(FilterType.ALL)
    val filterType: StateFlow<FilterType> = _filterType

    init {
        loadMovies()
        observeMovies()
    }

    private fun observeMovies() {
        viewModelScope.launch {
            combine(
                repository.getMovies(),
                _filterType
            ) { movies, filter ->
                when (filter) {
                    FilterType.ALL -> movies
                    FilterType.FAVORITES -> movies.filter { it.isFavorite }
                }
            }
                .catch { e ->
                    _state.value = UiState.Error(e.message ?: "Unknown error occurred")
                }
                .collect { filteredMovies ->
                    _state.value = UiState.Success(filteredMovies)
                }
        }
    }

    private fun loadMovies() {
        viewModelScope.launch {
            try {
                _state.value = UiState.Loading
                repository.refresh()
            } catch (e: Exception) {
                _state.value = UiState.Error(e.message ?: "Failed to load movies")
            }
        }
    }

    fun toggle(movie: Movie) {
        viewModelScope.launch {
            try {
                repository.toggleFavorite(movie.id, !movie.isFavorite)
            } catch (e: Exception) {
                // Handle error if needed
            }
        }
    }

    fun retry() {
        loadMovies()
    }

    fun setFilter(filter: FilterType) {
        _filterType.value = filter
    }
}