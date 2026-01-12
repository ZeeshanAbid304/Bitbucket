package com.example.db.presentation.movie_list


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.db.databinding.ItemMovieBinding
import com.example.db.domain.model.Movie

class MovieAdapter(
    private val onFav: (Movie) -> Unit,
    private val onClick: (Movie) -> Unit
) : ListAdapter<Movie, MovieAdapter.VH>(DIFF) {

    inner class VH(val b: ItemMovieBinding) : RecyclerView.ViewHolder(b.root) {
        init {
            b.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onClick(getItem(position))
                }
            }
        }
    }

    override fun onCreateViewHolder(p: ViewGroup, v: Int) =
        VH(ItemMovieBinding.inflate(LayoutInflater.from(p.context), p, false))

    override fun onBindViewHolder(h: VH, i: Int) = with(h.b) {
        val m = getItem(i)
        title.text = m.title
        year.text = m.releaseYear
        fav.isChecked = m.isFavorite
        Glide.with(image).load(m.image).into(image)
        fav.setOnClickListener { onFav(m) }
    }

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(a: Movie, b: Movie) = a.id == b.id
            override fun areContentsTheSame(a: Movie, b: Movie) = a == b
        }
    }
}