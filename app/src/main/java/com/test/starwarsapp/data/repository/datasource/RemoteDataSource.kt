package com.test.starwarsapp.data.datasource

import com.test.starwarsapp.data.ApiService
import com.test.starwarsapp.data.Wrapper
import com.test.starwarsapp.data.entities.CharacterEntity
import com.test.starwarsapp.data.entities.MovieEntity
import com.test.starwarsapp.data.entities.PlanetEntity
import com.test.starwarsapp.data.entities.SpecieEntity
import io.reactivex.Single
import javax.inject.Inject

/**
 * This data source is used to fetch the data from the api. It must be executed
 * in a worker thread.
 *
 * */
class RemoteDataSource(@Inject private val service: ApiService) {

    /**
     * Returns the list of all movies from Star Wars api.
     *
     * @return A list of movies.
     **/
    fun getMovies(): Single<List<MovieEntity>> {
        return service.getMovies()
                .map { wrapper -> unWrapper(wrapper)}
    }

    /**
     * Searches for a character from Star Wars api.
     *
     * @param query Whatever part of the character's name
     * @return A list of characters.
     * */
    fun searchCharacters(query: String): Single<List<CharacterEntity>> {
        return service.searchCharacters(query)
                .map { wrapper -> unWrapper(wrapper)}
    }

    /**
     * Gets a specific specie from Star Wars api.
     *
     * @param speciesId The specie id number.
     * @return The desired specie.
     * */
    fun getSpecies(speciesId: Int): Single<SpecieEntity> {
        return service.getSpecies(speciesId)
    }

    /**
     * Gets a specific planet from Star Wars api.
     *
     * @param planetId The planet number.
     * @return The desired planet.
     * */
    fun getPlanet(planetId: Int): Single<PlanetEntity> {
        return service.getPlanet(planetId)
    }

    private fun <T> unWrapper(wrapper: Wrapper<T>): List<T> {
        return wrapper.results;
    }

}