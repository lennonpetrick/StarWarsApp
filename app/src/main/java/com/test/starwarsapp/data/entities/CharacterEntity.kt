package com.test.starwarsapp.data.entities

import com.google.gson.annotations.SerializedName

data class CharacterEntity(
        val url: String,
        val name: String,
        val birthYear: String,
        val height: String,
        val films: List<String> = listOf(),
        val species: List<String> = listOf(),
        @SerializedName("homeworld") val planet: String
)