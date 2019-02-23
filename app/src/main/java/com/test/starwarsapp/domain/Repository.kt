package com.test.starwarsapp.domain

import com.test.starwarsapp.data.entities.CharacterEntity
import com.test.starwarsapp.data.entities.MovieEntity
import com.test.starwarsapp.data.entities.PlanetEntity
import com.test.starwarsapp.data.entities.SpecieEntity
import io.reactivex.Single

interface Repository {
    fun getMovies(): Single<List<MovieEntity>>
    fun getMovie(movieId: Int): Single<MovieEntity>
    fun searchCharacters(query: String): Single<List<CharacterEntity>>
    fun getCharacter(characterId: Int): Single<CharacterEntity>
    fun getSpecies(speciesId: Int): Single<SpecieEntity>
    fun getPlanet(planetId: Int): Single<PlanetEntity>
}