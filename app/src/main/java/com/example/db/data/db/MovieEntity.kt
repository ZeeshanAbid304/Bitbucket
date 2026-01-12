package com.example.db.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey val id: String,
    val title: String,
    val description: String,
    val image: String,
    val releaseYear: String,
    val isFavorite: Boolean
)