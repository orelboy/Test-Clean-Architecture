package com.practicum.testcleanarchitecture.domain.impl

import com.practicum.testcleanarchitecture.domain.api.MoviesInteractor
import com.practicum.testcleanarchitecture.domain.api.MoviesRepository
import java.util.concurrent.Executors

class MoviesInteractorImpl(private val repository: MoviesRepository) : MoviesInteractor {

    private val executor = Executors.newCachedThreadPool()

    override fun searchMovies(expression: String, consumer: MoviesInteractor.MoviesConsumer) {
        executor.execute {
            consumer.consume(repository.searchMovies(expression))
        }
    }
    /*
    или
        override fun searchMovies(expression: String, consumer: MoviesInteractor.MoviesConsumer) {
        val t = Thread {
            consumer.consume(repository.searchMovies(expression))
        }
        t.start()
    }
     */
}