package com.example.db.presentation.movie_list

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.db.R
import com.example.db.data.mapper.hide
import com.example.db.data.mapper.show
import com.example.db.databinding.ActivityMovieListBinding
import com.example.db.presentation.movie_details.MovieDetailsActivity
import com.example.db.presentation.ui_state.UiState
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieListActivity : AppCompatActivity() {

    private val vm: MovieListViewModel by viewModels()
    private lateinit var binding: ActivityMovieListBinding

    private val adapter = MovieAdapter(
        onFav = { vm.toggle(it) },
        onClick = { movie ->
            startActivity(
                Intent(this, MovieDetailsActivity::class.java).apply {
                    putExtra(MovieDetailsActivity.EXTRA_MOVIE_ID, movie.id)
                }
            )
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.recycler.adapter = adapter
        binding.filterToggle.check(R.id.btnAll)

        binding.filterToggle.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (!isChecked) return@addOnButtonCheckedListener

            when (checkedId) {
                R.id.btnAll -> vm.setFilter(FilterType.ALL)
                R.id.btnFavorites -> vm.setFilter(FilterType.FAVORITES)
            }
        }

        binding.retryButton.setOnClickListener {
            vm.retry()
        }

        observeState()
        setupExitDialog()
    }
    private fun setupExitDialog() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                showExitDialog()
            }
        })
    }
    private fun showExitDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Exit App")
            .setMessage("Are you sure you want to exit?")
            .setPositiveButton("Exit") { _, _ ->
                finish()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
    private fun observeState() {
        // Use lifecycleScope + repeatOnLifecycle to collect safely
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.state.collect { state ->
                    when (state) {
                        UiState.Loading -> {
                            binding.progress.show()
                            binding.recycler.hide()
                            binding.errorLayout.hide()
                        }

                        is UiState.Success -> {
                            binding.progress.hide()
                            binding.errorLayout.hide()
                            binding.recycler.show()

                            binding.emptyText.visibility =
                                if (state.data.isEmpty()) View.VISIBLE else View.GONE

                            adapter.submitList(state.data)
                        }

                        is UiState.Error -> {
                            binding.progress.hide()
                            binding.recycler.hide()
                            binding.errorLayout.show()
                            binding.errorText.text = state.message ?: "Unknown error"
                        }
                    }
                }
            }
        }
    }
}