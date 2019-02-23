package com.test.starwarsapp.domain.usecase

import com.test.starwarsapp.di.IOScheduler
import com.test.starwarsapp.di.UIScheduler
import com.test.starwarsapp.domain.Repository
import com.test.starwarsapp.domain.models.Character
import com.test.starwarsapp.domain.models.mapper.transformCharacter
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.flatMapIterable
import javax.inject.Inject


/**
 * This class is an implementation of [UseCase] which searches
 * for a character that matches the query.
 *
 */
class SearchCharacters @Inject constructor (
        private val repository: Repository,
        @IOScheduler workThread: Scheduler,
        @UIScheduler uiThread: Scheduler,
        disposables: CompositeDisposable
)
    : UseCase<List<Character>, SearchCharacters.Params>(
        workThread,
        uiThread,
        disposables
) {
    override fun buildUseCase(params: Params): Single<List<Character>> {
        return repository.searchCharacters(params.query)
                .toObservable()
                .flatMapIterable()
                .map { transformCharacter(it) }
                .toList()
    }

    class Params private constructor(internal val query: String) {
        companion object {
            fun forQuery(query: String): Params {
                return Params(query)
            }
        }
    }
}