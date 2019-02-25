package com.test.starwarsapp.presentation.character.detail

import com.test.starwarsapp.domain.models.Movie
import com.test.starwarsapp.presentation.base.BaseContract

interface CharacterDetailContract {
    interface View : BaseContract.View {
        fun setContentVisibility(param: Boolean)
        fun setName(name: String)
        fun setBirth(birth: String)
        fun setPlanet(planet: String)
        fun setHeight(height: String)
        fun setSpecies(species: String)
        fun setSpecieLanguage(language: String)
        fun setSpeciePlanet(planet: String)
        fun setSpeciePlanetPopulation(population: String)
        fun setMovies(movies: List<Movie>)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun setCharacterUrl(url: String)
    }
}