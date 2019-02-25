package com.test.starwarsapp.presentation.moviedetail

import com.test.starwarsapp.domain.models.Movie
import com.test.starwarsapp.presentation.base.BasePresenter
import com.test.starwarsapp.utils.displayDate
import javax.inject.Inject

class MovieDetailPresenter @Inject constructor() :
        BasePresenter<MovieDetailContract.View>(), MovieDetailContract.Presenter {

    private lateinit var movie: Movie

    override fun setMovie(movie: Movie) {
        this.movie = movie
    }

    override fun load() {
        view?.apply {
            this.setTitle(movie.title)
            this.setSubTitle(movie.episodeId, displayDate(movie.releaseDate))
            this.setDirector(movie.director)
            this.setProducer(movie.producer)
            this.setOpeningCrawl(movie.openingCrawl)
        }
    }

}