package com.practicum.testcleanarchitecture.ui.movies

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.practicum.testcleanarchitecture.util.Creator
import com.practicum.testcleanarchitecture.ui.poster.PosterActivity
import com.practicum.testcleanarchitecture.R

class MoviesActivity : Activity() {

    private val adapter = MoviesAdapter {
        if (clickDebounce()) {
            val intent = Intent(this, PosterActivity::class.java)
            intent.putExtra("poster", it.image)
            startActivity(intent)
        }
    }

    private var isClickAllowed = true

    private val handler = Handler(Looper.getMainLooper())

    private val moviesSearchController = Creator.provideMoviesSearchController(this, adapter)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
        moviesSearchController.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        moviesSearchController.onDestroy()
    }

    private fun clickDebounce(): Boolean {
        val current = isClickAllowed
        if (isClickAllowed) {
            isClickAllowed = false
            handler.postDelayed({ isClickAllowed = true }, CLICK_DEBOUNCE_DELAY)
        }
        return current
    }

    companion object {
        private const val CLICK_DEBOUNCE_DELAY = 1000L
    }
}