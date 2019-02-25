package com.test.starwarsapp.presentation.home

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.test.starwarsapp.R
import com.test.starwarsapp.domain.models.Movie
import com.test.starwarsapp.presentation.base.BaseActivity
import com.test.starwarsapp.presentation.base.adapter.BaseAdapter
import com.test.starwarsapp.presentation.home.adapter.MoviesAdapter
import com.test.starwarsapp.presentation.moviedetail.MovieDetailActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity<HomeContract.Presenter>(), HomeContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setUpToolbar(false)
        setUpRecyclerView()

        presenter.view(this)
        presenter.load()
    }

    private fun setUpRecyclerView() {
        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)

        getDrawable(R.drawable.layout_recycler_divider_transparent)?.apply {
            divider.setDrawable(this)
        }

        rvMovies.addItemDecoration(divider)
        rvMovies.layoutManager = LinearLayoutManager(this)
        rvMovies.setHasFixedSize(false)
    }

    override fun setMovies(movies: List<Movie>) {
        rvMovies.adapter = MoviesAdapter(movies).apply {
            this.onItemClickListener = object: BaseAdapter.OnItemClickListener<Movie> {
                override fun onItemClick(item: Movie) {
                    startMovieDetailScreen(item)
                }
            }
        }
    }

    fun startMovieDetailScreen(movie: Movie) {
        startActivity(
                Intent(this, MovieDetailActivity::class.java).also {
                    it.putExtra(Movie::class.java.name, movie)
                }
        )
    }
}
