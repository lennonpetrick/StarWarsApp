package com.test.starwarsapp.presentation.moviedetail

import android.os.Bundle
import com.test.starwarsapp.R
import com.test.starwarsapp.domain.models.Movie
import com.test.starwarsapp.presentation.base.BaseActivity
import kotlinx.android.synthetic.main.activity_movie_detail.*
import java.util.*

class MovieDetailActivity : BaseActivity<MovieDetailContract.Presenter>(),
        MovieDetailContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        setUpToolbar(true)

        getMovieFromExtra(intent.extras)?.apply {
            presenter.view(this@MovieDetailActivity)
            presenter.setMovie(this)
            presenter.load()
        }
    }

    override fun setTitle(title: String) {
        tvTitle.text = title
    }

    override fun setSubTitle(episode: Int, date: String) {
        tvSubTitle.text = String.format(
                Locale.getDefault(),
                getString(R.string.movie_detail_subtitle),
                episode,
                date)
    }

    override fun setDirector(director: String) {
        tvDirector.text = String.format(
                Locale.getDefault(),
                getString(R.string.movie_detail_director),
                director)
    }

    override fun setProducer(producer: String) {
        tvProducer.text = String.format(
                Locale.getDefault(),
                getString(R.string.movie_detail_producer),
                producer)
    }

    override fun setOpeningCrawl(text: String) {
        tvOpeningCrawl.text = text
    }

    private fun getMovieFromExtra(extras: Bundle?): Movie? {
        return extras?.getParcelable(Movie::class.java.name)
    }
}
