package com.test.starwarsapp.domain.models

data class Specie(
        val name: String,
        val language: String,
        var planet: Planet? = null
)