package com.example.db.`data`.db

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import javax.`annotation`.processing.Generated
import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class MovieDao_Impl(
  __db: RoomDatabase,
) : MovieDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfMovieEntity: EntityInsertAdapter<MovieEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfMovieEntity = object : EntityInsertAdapter<MovieEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `movies` (`id`,`title`,`description`,`image`,`releaseYear`,`isFavorite`) VALUES (?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: MovieEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.title)
        statement.bindText(3, entity.description)
        statement.bindText(4, entity.image)
        statement.bindText(5, entity.releaseYear)
        val _tmp: Int = if (entity.isFavorite) 1 else 0
        statement.bindLong(6, _tmp.toLong())
      }
    }
  }

  public override suspend fun insertAll(movies: List<MovieEntity>): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfMovieEntity.insert(_connection, movies)
  }

  public override fun getMovies(): Flow<List<MovieEntity>> {
    val _sql: String = "SELECT * FROM movies"
    return createFlow(__db, false, arrayOf("movies")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _columnIndexOfImage: Int = getColumnIndexOrThrow(_stmt, "image")
        val _columnIndexOfReleaseYear: Int = getColumnIndexOrThrow(_stmt, "releaseYear")
        val _columnIndexOfIsFavorite: Int = getColumnIndexOrThrow(_stmt, "isFavorite")
        val _result: MutableList<MovieEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: MovieEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          val _tmpImage: String
          _tmpImage = _stmt.getText(_columnIndexOfImage)
          val _tmpReleaseYear: String
          _tmpReleaseYear = _stmt.getText(_columnIndexOfReleaseYear)
          val _tmpIsFavorite: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsFavorite).toInt()
          _tmpIsFavorite = _tmp != 0
          _item = MovieEntity(_tmpId,_tmpTitle,_tmpDescription,_tmpImage,_tmpReleaseYear,_tmpIsFavorite)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun updateFavorite(id: String, fav: Boolean) {
    val _sql: String = "UPDATE movies SET isFavorite = ? WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        val _tmp: Int = if (fav) 1 else 0
        _stmt.bindLong(_argIndex, _tmp.toLong())
        _argIndex = 2
        _stmt.bindText(_argIndex, id)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
