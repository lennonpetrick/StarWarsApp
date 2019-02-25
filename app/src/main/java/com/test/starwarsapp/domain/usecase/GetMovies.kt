package com.test.starwarsapp.domain.usecase

import com.test.starwarsapp.di.qualifiers.IOScheduler
import com.test.starwarsapp.di.qualifiers.UIScheduler
import com.test.starwarsapp.domain.Repository
import com.test.starwarsapp.domain.models.Movie
import com.test.starwarsapp.domain.models.mapper.transformMovie
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.flatMapIterable
import javax.inject.Inject

/**
 * This class is an implementation of [UseCase] which retrieves all movies
 * from Star Wars repository.
 *
 */
class GetMovies @Inject constructor (
        private val repository: Repository,
        @IOScheduler workThread: Scheduler,
        @UIScheduler uiThread: Scheduler,
        disposables: CompositeDisposable
)
    : UseCase<List<Movie>, Nothing>(
        workThread,
        uiThread,
        disposables
) {

    override fun buildUseCase(params: Nothing?): Single<List<Movie>> {
        return repository.getMovies()
                .toObservable()
                .flatMapIterable()
                .map { transformMovie(it) }
                .toList()
    }

}