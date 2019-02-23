package com.test.starwarsapp.data

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
     * @return A wrapper containing a list of movies.
     **/
    @GET("films/")
    fun getMovies(): Single<Wrapper<MovieEntity>>

    /**
     * Searches for a character from Star Wars universe.
     *
     * @param query Whatever part of the character's name
     * @return A wrapper containing a list of characters.
     * */
    @GET("people/")
    fun searchCharacters(@Query("search") query: String): Single<Wrapper<CharacterEntity>>

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
    @GET("planets/{planetId}")
    fun getPlanet(@Path("planetId") planetId: Int): Single<PlanetEntity>

}