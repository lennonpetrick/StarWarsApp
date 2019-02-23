package com.test.starwarsapp.data

data class Wrapper<T>(
        val count: Int,
        var results: List<T> = listOf()
)