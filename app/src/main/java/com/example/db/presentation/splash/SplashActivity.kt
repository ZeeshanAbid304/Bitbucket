package com.example.db.presentation.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.db.databinding.ActivitySplashBinding
import com.example.db.presentation.movie_list.MovieListActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Navigate to Dashboard after 2 seconds
        binding.main.postDelayed({
            startActivity(Intent(this, MovieListActivity::class.java))
            finish()
        }, 2000)
    }
}