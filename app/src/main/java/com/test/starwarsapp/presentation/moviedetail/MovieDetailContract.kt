package com.test.starwarsapp.presentation.moviedetail

import com.test.starwarsapp.domain.models.Movie
import com.test.starwarsapp.presentation.base.BaseContract

interface MovieDetailContract {
    interface View : BaseContract.View {
        fun setTitle(title: String)
        fun setSubTitle(episode: Int, date: String)
        fun setDirector(director: String)
        fun setProducer(producer: String)
        fun setOpeningCrawl(text: String)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun setMovie(movie: Movie)
    }
}