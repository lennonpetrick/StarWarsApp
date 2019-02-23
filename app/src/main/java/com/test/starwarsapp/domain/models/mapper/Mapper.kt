package com.test.starwarsapp.domain.models.mapper

import com.test.starwarsapp.data.entities.CharacterEntity
import com.test.starwarsapp.data.entities.MovieEntity
import com.test.starwarsapp.data.entities.PlanetEntity
import com.test.starwarsapp.data.entities.SpecieEntity
import com.test.starwarsapp.domain.models.Character
import com.test.starwarsapp.domain.models.Movie
import com.test.starwarsapp.domain.models.Planet
import com.test.starwarsapp.domain.models.Specie
import java.text.SimpleDateFormat
import java.util.*

internal fun transformMovie(entity: MovieEntity): Movie {
    return Movie(
            title = entity.title,
            director = entity.director,
            producer = entity.producer,
            openingCrawl = entity.openingCrawl,
            episodeId = entity.episodeId,
            releaseDate = entity.releaseDate.let {
                SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                        .parse(it)
            }
    )
}

internal fun transformCharacter(entity: CharacterEntity): Character {
    return Character(
            name = entity.name,
            birthYear = entity.birthYear,
            height = entity.height
    )
}

internal fun transformSpecies(entity: SpecieEntity): Specie {
    return Specie(
            name = entity.name,
            language = entity.language
    )
}

internal fun transformPlanet(entity: PlanetEntity): Planet {
    return Planet(
            name = entity.name,
            population = entity.population
    )
}