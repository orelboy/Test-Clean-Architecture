package com.practicum.testcleanarchitecture.presentation.movies

import android.content.Context
import android.os.Handler
import android.os.Looper
import com.practicum.testcleanarchitecture.R
import com.practicum.testcleanarchitecture.domain.api.MoviesInteractor
import com.practicum.testcleanarchitecture.domain.models.Movie
import com.practicum.testcleanarchitecture.ui.movies.models.MoviesState
import com.practicum.testcleanarchitecture.util.Creator
import moxy.MvpPresenter

class MoviesSearchPresenter(
    private val context: Context,
) : MvpPresenter<MoviesView>() {

    private val moviesInteractor = Creator.provideMoviesInteractor(context)
    private val handler = Handler(Looper.getMainLooper())

    private var lastSearchText: String? = null

    private val searchRunnable = Runnable {
        val newSearchText = lastSearchText ?: ""
        searchRequest(newSearchText)
    }

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
        private val SEARCH_REQUEST_TOKEN = Any()

    }

    override fun onDestroy() {
        handler.removeCallbacksAndMessages(SEARCH_REQUEST_TOKEN)
    }

    fun searchDebounce(changedText: String) {
        if (lastSearchText == changedText) {
            return
        }

        this.lastSearchText = changedText
        handler.removeCallbacks(searchRunnable)
        handler.postDelayed(searchRunnable, SEARCH_DEBOUNCE_DELAY)
    }

    private fun searchRequest(newSearchText: String) {
        if (newSearchText.isNotEmpty()) {

            renderState(MoviesState.Loading)

            moviesInteractor.searchMovies(
                newSearchText,
                object : MoviesInteractor.MoviesConsumer {
                    override fun consume(foundMovies: List<Movie>?, errorMessage: String?) {
                        handler.post {
                            val movies = mutableListOf<Movie>()
                            if (foundMovies != null) {
                                movies.clear()
                                movies.addAll(foundMovies)
                            }

                            when {
                                errorMessage != null -> {
                                    renderState(
                                        MoviesState.Error(
                                            errorMessage = context.getString(R.string.something_went_wrong),
                                        )
                                    )
                                    viewState.showToast(errorMessage)
                                }

                                movies.isEmpty() -> {
                                    renderState(
                                        MoviesState.Empty(
                                            message = context.getString(R.string.nothing_found),
                                        )
                                    )
                                }

                                else -> {
                                    renderState(
                                        MoviesState.Content(
                                            movies = movies,
                                        )
                                    )
                                }
                            }
                        }
                    }
                })
        }
    }

    private fun renderState(state: MoviesState) {
        viewState.render(state)
    }
}