package com.test.starwarsapp.presentation.home

import com.test.starwarsapp.domain.models.Movie
import com.test.starwarsapp.presentation.base.BaseContract

interface HomeContract {
    interface View : BaseContract.View {
        fun setMovies(movies: List<Movie>)
    }

    interface Presenter : BaseContract.Presenter<View>
}