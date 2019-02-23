package com.test.starwarsapp.data.repository.datasource

import com.test.starwarsapp.data.entities.CharacterEntity
import com.test.starwarsapp.data.entities.MovieEntity
import com.test.starwarsapp.data.entities.PlanetEntity
import com.test.starwarsapp.data.entities.SpecieEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * This interface is a service used for getting the data from api using
 * retrofit. The framework uses this interface.
 *
 */
interface ApiService {

    /**
     * Returns the list of all movies from Star Wars.
     *
     * @return A [Wrapper] containing a list of movies.
     **/
    @GET("films/")
    fun getMovies(): Single<Wrapper<MovieEntity>>

    /**
     * Returns a specific movie from Star Wars universe.
     *
     * @param movieId The movie number.
     * @return The desired movie.
     **/
    @GET("films/{movieId}")
    fun getMovie(@Path("movieId") movieId: Int): Single<MovieEntity>

    /**
     * Searches for a character from Star Wars universe.
     *
     * @param query Whatever part of the character's name
     * @return A [Wrapper] containing a list of characters.
     * */
    @GET("people/")
    fun searchCharacters(@Query("search") query: String): Single<Wrapper<CharacterEntity>>

    /**
     * Returns a specific character from Star Wars universe.
     *
     * @param characterId The character id.
     * @return The desired character.
     **/
    @GET("people/{characterId}")
    fun getCharacter(@Path("characterId") characterId: Int): Single<CharacterEntity>

    /**
     * Gets a specific specie from Star Wars universe.
     *
     * @param speciesId The specie id number.
     * @return The desired specie.
     * */
    @GET("species/{speciesId}")
    fun getSpecies(@Path("speciesId") speciesId: Int): Single<SpecieEntity>

    /**
     * Gets a specific planet from Star Wars universe.
     *
     * @param planetId The planet number.
     * @return The desired planet.
     * */
    @GET("planets/{id}")
    fun getPlanet(@Path("id") planetId: Int): Single<PlanetEntity>

}