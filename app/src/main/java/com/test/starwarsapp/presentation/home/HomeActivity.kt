package com.test.starwarsapp.presentation.home

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.test.starwarsapp.R
import com.test.starwarsapp.domain.models.Movie
import com.test.starwarsapp.presentation.base.BaseActivity
import com.test.starwarsapp.presentation.base.adapter.BaseAdapter
import com.test.starwarsapp.presentation.character.search.CharacterSearchActivity
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_action_characters -> {
                startCharacterSearchScreen()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
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
        rvMovies.adapter = MoviesAdapter(movies.toMutableList()).apply {
            this.onItemClickListener = object: BaseAdapter.OnItemClickListener<Movie> {
                override fun onItemClick(item: Movie) {
                    startMovieDetailScreen(item)
                }
            }
        }
    }

    private fun startMovieDetailScreen(movie: Movie) {
        startActivity(
                Intent(this, MovieDetailActivity::class.java).also {
                    it.putExtra(Movie::class.java.name, movie)
                }
        )
    }

    private fun startCharacterSearchScreen() {
        startActivity(Intent(this,
                CharacterSearchActivity::class.java))
    }
}
