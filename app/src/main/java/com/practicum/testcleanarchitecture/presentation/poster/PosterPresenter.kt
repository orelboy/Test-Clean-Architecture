package com.practicum.testcleanarchitecture.presentation.poster

class PosterPresenter (
    private val view: PosterView,
    private val imageUrl: String,
    ) {

    fun onCreate() {
        view.setupPosterImage(imageUrl)
    }
}