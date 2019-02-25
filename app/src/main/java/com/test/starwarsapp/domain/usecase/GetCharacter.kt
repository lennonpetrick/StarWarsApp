package com.test.starwarsapp.domain.usecase

import android.net.Uri
import com.test.starwarsapp.di.qualifiers.IOScheduler
import com.test.starwarsapp.di.qualifiers.UIScheduler
import com.test.starwarsapp.domain.Repository
import com.test.starwarsapp.domain.models.Character
import com.test.starwarsapp.domain.models.Movie
import com.test.starwarsapp.domain.models.Planet
import com.test.starwarsapp.domain.models.Specie
import com.test.starwarsapp.domain.models.mapper.transformCharacter
import com.test.starwarsapp.domain.models.mapper.transformMovie
import com.test.starwarsapp.domain.models.mapper.transformPlanet
import com.test.starwarsapp.domain.models.mapper.transformSpecies
import com.test.starwarsapp.domain.usecase.GetCharacter.Params.Companion.getId
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function4
import javax.inject.Inject

/**
 * This class is an implementation of [UseCase] which retrieves a
 * specific character.
 *
 */
class GetCharacter @Inject constructor (
        private val repository: Repository,
        @IOScheduler workThread: Scheduler,
        @UIScheduler uiThread: Scheduler,
        disposables: CompositeDisposable
)
    : UseCase<Character, GetCharacter.Params>(
        workThread,
        uiThread,
        disposables
) {
    override fun buildUseCase(params: Params?): Single<Character> {
        return repository.getCharacter(params!!.id)
                .observeOn(workThread)
                .flatMap {
                    Single.zip(
                            Single.just(transformCharacter(it)),
                            getMovies(it.films),
                            getSpecies(it.species),
                            getPlanet(it.planet),
                            zipCharacter()
                    )
                }
    }

    private fun getMovies(urls: List<String>): Single<List<Movie>> {
        return Observable.fromIterable(urls)
                .flatMapSingle {
                    repository.getMovie(getId(it))
                }
                .map { transformMovie(it) }
                .toList()
    }

    private fun getSpecies(urls: List<String>): Single<List<Specie>> {
        return Observable.fromIterable(urls)
                .flatMapSingle {url ->
                    repository.getSpecies(getId(url))
                            .flatMap {
                                Single.zip(
                                        Single.just(transformSpecies(it)),
                                        getPlanet(it.planet),
                                        BiFunction<Specie, Planet, Specie> { specie, planet ->
                                            specie.planet = planet
                                            specie
                                        }
                                )
                            }
                }
                .toList()
    }

    private fun getPlanet(url: String): Single<Planet> {
        return repository.getPlanet(getId(url))
                .map { transformPlanet(it) }
    }

    private fun zipCharacter() = Function4<Character, List<Movie>,
            List<Specie>, Planet, Character>
    { character, movies, species, planet ->
        character.films = movies
        character.species = species
        character.planet = planet
        character
    }

    class Params private constructor(internal val id: Int) {
        companion object {
            fun forUrl(url: String): Params {
                return Params(getId(url))
            }

            internal fun getId(url: String): Int {
                return Uri.parse(url).lastPathSegment!!.toInt()
            }
        }
    }
}