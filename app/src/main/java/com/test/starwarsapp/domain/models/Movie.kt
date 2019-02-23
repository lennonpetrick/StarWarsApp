package com.test.starwarsapp.domain.models

import java.util.*

data class Movie(
        val title: String,
        val releaseDate: Date,
        val director: String,
        val producer: String,
        val episodeId: Int,
        val openingCrawl: String
)