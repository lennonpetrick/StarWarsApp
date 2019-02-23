package com.test.starwarsapp.data.entities

import com.google.gson.annotations.SerializedName

data class SpecieEntity(
        val url: String,
        val name: String,
        val language: String,
        @SerializedName("homeworld") val planet: String
)