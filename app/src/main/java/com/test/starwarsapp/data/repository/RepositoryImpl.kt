package com.test.starwarsapp.data.repository

import com.test.starwarsapp.data.entities.CharacterEntity
import com.test.starwarsapp.data.entities.MovieEntity
import com.test.starwarsapp.data.entities.PlanetEntity
import com.test.starwarsapp.data.entities.SpecieEntity
import com.test.starwarsapp.data.repository.datasource.RemoteDataSource
import com.test.starwarsapp.domain.Repository
import io.reactivex.Single
import javax.inject.Inject

/**
 * This class is a implementation of [Repository] and it is responsible for
 * retrieving data from local and cloud. In this case, it is only fetching in the cloud.
 *
 * Must be executed in a worker thread.
 *
 * */
class RepositoryImpl(@Inject private val dataSource: RemoteDataSource) : Repository {

    /**
     * Returns the list of all movies from Star Wars data source.
     *
     * @return A list of movies.
     **/
    override fun getMovies(): Single<List<MovieEntity>> {
        return dataSource.getMovies()
    }

    /**
     * Gets a specific movie from Star Wars data source.
     *
     * @param movieId The movie number.
     * @return The desired movie.
     **/
    override fun getMovie(movieId: Int): Single<MovieEntity> {
        return dataSource.getMovie(movieId)
    }

    /**
     * Searches for a character from Star Wars data source.
     *
     * @param query Whatever part of the character's name
     * @return A list of characters.
     * */
    override fun searchCharacters(query: String): Single<List<CharacterEntity>> {
        return dataSource.searchCharacters(query)
    }

    /**
     * Gets a specific character from Star Wars data source.
     *
     * @param characterId The character id.
     * @return The desired character.
     **/
    override fun getCharacter(characterId: Int): Single<CharacterEntity> {
        return dataSource.getCharacter(characterId)
    }

    /**
     * Gets a specific specie from Star Wars data source.
     *
     * @param speciesId The specie id number.
     * @return The desired specie.
     * */
    override fun getSpecies(speciesId: Int): Single<SpecieEntity> {
        return dataSource.getSpecies(speciesId)
    }

    /**
     * Gets a specific planet from Star Wars data source.
     *
     * @param planetId The planet number.
     * @return The desired planet.
     * */
    override fun getPlanet(planetId: Int): Single<PlanetEntity> {
        return dataSource.getPlanet(planetId)
    }
}