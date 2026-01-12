package com.example.db.domain.model

data class Movie(
    val id: String,
    val title: String,
    val description: String,
    val image: String,
    val releaseYear: String,
    val isFavorite: Boolean
)