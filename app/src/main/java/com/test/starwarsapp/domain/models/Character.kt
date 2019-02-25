package com.test.starwarsapp.domain.models

data class Character(
        val url: String,
        val name: String,
        val birthYear: String,
        val height: String,
        var films: List<Movie>? = null,
        var species: List<Specie>? = null,
        var planet: Planet? = null
)