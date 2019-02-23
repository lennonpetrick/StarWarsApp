package com.test.starwarsapp.data.entities

import com.google.gson.annotations.SerializedName

data class MovieEntity(
        val url: String,
        val title: String,
        val director: String,
        val producer: String,
        @SerializedName("release_date") val releaseDate: String,
        @SerializedName("episode_id") val episodeId: Int,
        @SerializedName("opening_crawl") val openingCrawl: String
)