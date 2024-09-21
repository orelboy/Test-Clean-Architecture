package com.practicum.testcleanarchitecture.ui.movies

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.practicum.testcleanarchitecture.Creator
import com.practicum.testcleanarchitecture.ui.poster.PosterActivity
import com.practicum.testcleanarchitecture.R
import com.practicum.testcleanarchitecture.data.MoviesRepositoryImpl
import com.practicum.testcleanarchitecture.data.dto.MoviesSearchResponse
import com.practicum.testcleanarchitecture.data.network.IMDbApiService
import com.practicum.testcleanarchitecture.domain.api.MoviesInteractor
import com.practicum.testcleanarchitecture.domain.impl.MoviesInteractorImpl
import com.practicum.testcleanarchitecture.domain.models.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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