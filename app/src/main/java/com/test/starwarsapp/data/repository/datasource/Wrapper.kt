package com.test.starwarsapp.data.repository.datasource

data class Wrapper<T>(
        val count: Int,
        var results: List<T> = listOf()
)