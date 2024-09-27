package com.practicum.testcleanarchitecture.presentation.movies

import com.practicum.testcleanarchitecture.ui.movies.models.MoviesState
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface MoviesView : MvpView {
    // Сюда мы будем добавлять методы
    // для взаимодействия View и Presenter

    // Методы, меняющие внешний вид экрана
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun render(state: MoviesState)

    // Методы «одноразовых событий»
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showToast(additionalMessage: String)
}
