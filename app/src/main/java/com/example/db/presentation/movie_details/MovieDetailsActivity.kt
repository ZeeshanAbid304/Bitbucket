package com.example.db.presentation.movie_details


import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.db.R
import com.example.db.data.mapper.hide
import com.example.db.data.mapper.show
import com.example.db.databinding.ActivityMovieDetailsBinding
import com.example.db.presentation.ui_state.UiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsActivity : AppCompatActivity() {

    private val vm: MovieDetailsViewModel by viewModels()
    private lateinit var binding: ActivityMovieDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Get movie ID from intent
        val movieId = intent.getStringExtra(EXTRA_MOVIE_ID)
        if (movieId == null) {
            Toast.makeText(this, "Movie not found", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        vm.loadMovie(movieId)

        setupUI()
        observeState()
    }

    private fun setupUI() {
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        binding.fabFavorite.setOnClickListener {
            vm.toggleFavorite()
        }
    }

    private fun observeState() {
        lifecycleScope.launchWhenStarted {
            vm.state.collect { state ->
                when (state) {
                    UiState.Loading -> {
                        binding.progress.show()
                        binding.contentGroup.hide()
                    }

                    is UiState.Success -> {
                        binding.progress.hide()
                        binding.contentGroup.show()

                        val movie = state.data
                        binding.apply {
                            title.text = movie.title
                            year.text = movie.releaseYear
                            description.text = movie.description

                            Glide.with(poster)
                                .load(movie.image)
                                .into(poster)

                            updateFavoriteButton(movie.isFavorite)
                        }
                    }

                    is UiState.Error -> {
                        binding.progress.hide()
                        Toast.makeText(
                            this@MovieDetailsActivity,
                            state.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }

    private fun updateFavoriteButton(isFavorite: Boolean) {
        binding.fabFavorite.setImageResource(
            if (isFavorite) R.drawable.ic_star_fill
            else R.drawable.ic_star_empty
        )
    }

    companion object {
        const val EXTRA_MOVIE_ID = "extra_movie_id"
    }
}