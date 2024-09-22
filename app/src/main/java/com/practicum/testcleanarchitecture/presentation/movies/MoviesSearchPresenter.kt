package com.practicum.testcleanarchitecture.presentation.movies

import android.content.Context
import android.os.Handler
import android.os.Looper
import com.practicum.testcleanarchitecture.R
import com.practicum.testcleanarchitecture.domain.api.MoviesInteractor
import com.practicum.testcleanarchitecture.domain.models.Movie
import com.practicum.testcleanarchitecture.ui.movies.models.MoviesState
import com.practicum.testcleanarchitecture.util.Creator

class MoviesSearchPresenter(
    private val view: MoviesView,
    private val context: Context,
) {

    private val moviesInteractor = Creator.provideMoviesInteractor(context)
    private val handler = Handler(Looper.getMainLooper())
    private val movies = ArrayList<Movie>()

    private var lastSearchText: String? = null

    private val searchRunnable = Runnable {
        val newSearchText = lastSearchText ?: ""
        searchRequest(newSearchText)
    }

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
        private val SEARCH_REQUEST_TOKEN = Any()

    }

    fun onDestroy() {
        handler.removeCallbacksAndMessages(SEARCH_REQUEST_TOKEN)
       // handler.removeCallbacks(searchRunnable)
    }

    fun searchDebounce(changedText: String) {
        this.lastSearchText = changedText
        handler.removeCallbacks(searchRunnable)
        handler.postDelayed(searchRunnable, SEARCH_DEBOUNCE_DELAY)
    }

    private fun searchRequest(newSearchText: String) {
        if (newSearchText.isNotEmpty()) {

            view.render(
                MoviesState.Loading
            )

            moviesInteractor.searchMovies(
                newSearchText,
                object : MoviesInteractor.MoviesConsumer {
                    override fun consume(foundMovies: List<Movie>?, errorMessage: String?) {
                        handler.post {
                            if (foundMovies != null) {
                                movies.clear()
                                movies.addAll(foundMovies)
                            }

                            when {
                                errorMessage != null -> {
                                    view.render(
                                        MoviesState.Error(
                                            errorMessage = context.getString(R.string.something_went_wrong),
                                        )
                                    )
                                    view.showToast(errorMessage)
                                }

                                movies.isEmpty() -> {
                                    view.render(
                                        MoviesState.Empty(
                                            message = context.getString(R.string.nothing_found),
                                        )
                                    )                                }

                                else -> {
                                    view.render(
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

//    private fun showMessage(text: String, additionalMessage: String) {
//        if (text.isNotEmpty()) {
//            view.showPlaceholderMessage(true)
//            movies.clear()
//            view.updateMoviesList(movies)
//            view.changePlaceholderText(text)
//            if (additionalMessage.isNotEmpty()) {
//                view.showMessage(additionalMessage)
//            }
//        } else {
//            view.showPlaceholderMessage(false)
//        }
//    }
//
//    private fun hideMessage() {
//        view.showPlaceholderMessage(false)
//    }
}