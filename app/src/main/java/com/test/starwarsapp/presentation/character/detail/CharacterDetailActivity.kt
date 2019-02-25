package com.test.starwarsapp.presentation.character.detail

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.test.starwarsapp.R
import com.test.starwarsapp.domain.models.Character
import com.test.starwarsapp.domain.models.Movie
import com.test.starwarsapp.presentation.base.BaseActivity
import com.test.starwarsapp.presentation.base.adapter.BaseAdapter
import com.test.starwarsapp.presentation.character.detail.adapter.CharacterMoviesAdapter
import com.test.starwarsapp.presentation.moviedetail.MovieDetailActivity
import kotlinx.android.synthetic.main.activity_character_detail.*
import kotlinx.android.synthetic.main.content_character_info.*
import kotlinx.android.synthetic.main.content_species.*

class CharacterDetailActivity : BaseActivity<CharacterDetailContract.Presenter>(),
        CharacterDetailContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)
        setUpToolbar(true)
        setUpRecyclerView()

        getCharacterUrl(intent.extras)?.apply {
            presenter.view(this@CharacterDetailActivity)
            presenter.setCharacterUrl(this)
            presenter.load()
        }
    }

    private fun setUpRecyclerView() {
        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)

        getDrawable(R.drawable.layout_recycler_divider_transparent)?.apply {
            divider.setDrawable(this)
        }

        rvCharactersMovies.addItemDecoration(divider)
        rvCharactersMovies.layoutManager = LinearLayoutManager(this)
        rvCharactersMovies.isNestedScrollingEnabled = false
        rvCharactersMovies.setHasFixedSize(false)
    }

    override fun setContentVisibility(param: Boolean) {
        characterContent.visibility = if (param) View.VISIBLE else View.GONE
    }

    override fun setName(name: String) {
        tvName.text = name
    }

    override fun setBirth(birth: String) {
        tvBirth.text = birth
    }

    override fun setPlanet(planet: String) {
        tvPlanet.text = planet
    }

    override fun setHeight(height: String) {
        tvHeight.text = height
    }

    override fun setSpecies(species: String) {
        tvSpecies.text = species
    }

    override fun setSpecieLanguage(language: String) {
        tvSpcLanguage.text = language
    }

    override fun setSpeciePlanet(planet: String) {
        tvSpcPlanet.text = planet
    }

    override fun setSpeciePlanetPopulation(population: String) {
        tvSpcPopulation.text = population
    }

    override fun setMovies(movies: List<Movie>) {
        rvCharactersMovies.adapter = CharacterMoviesAdapter(movies.toMutableList()).apply {
            this.onItemClickListener = object: BaseAdapter.OnItemClickListener<Movie> {
                override fun onItemClick(item: Movie) {
                    startMovieDetailScreen(item)
                }
            }
        }
    }

    private fun getCharacterUrl(extras: Bundle?): String? {
        return extras?.getString(Character::class.java.name)
    }

    private fun startMovieDetailScreen(movie: Movie) {
        startActivity(
                Intent(this, MovieDetailActivity::class.java).also {
                    it.putExtra(Movie::class.java.name, movie)
                }
        )
    }
}
