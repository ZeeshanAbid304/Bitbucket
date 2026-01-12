package com.example.db.data.mapper

import android.view.View
import com.example.db.data.api.MovieDto
import com.example.db.data.db.MovieEntity
import com.example.db.domain.model.Movie

fun MovieDto.toEntity() = MovieEntity(
    id, title, description, image, release_date, false
)


fun MovieEntity.toDomain() = Movie(
    id, title, description, image, releaseYear, isFavorite
)
fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}