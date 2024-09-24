package com.practicum.testcleanarchitecture

import android.app.Application
import com.practicum.testcleanarchitecture.presentation.movies.MoviesSearchPresenter

class MoviesApplication : Application() {

    var moviesSearchPresenter : MoviesSearchPresenter? = null

}