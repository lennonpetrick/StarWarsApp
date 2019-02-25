package com.test.starwarsapp.presentation.home.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.test.starwarsapp.R
import com.test.starwarsapp.domain.models.Movie
import com.test.starwarsapp.presentation.base.adapter.BaseAdapter
import com.test.starwarsapp.utils.displayDate
import kotlinx.android.synthetic.main.layout_movies_adapter.view.*

class MoviesAdapter(movies: List<Movie>) : BaseAdapter<Movie, MoviesAdapter.ViewHolder>(movies) {

    override fun getLayout(): Int {
        return R.layout.layout_movies_adapter
    }

    override fun getHolderView(view: View): ViewHolder {
        return ViewHolder(view)
    }

    override fun bind(holder: ViewHolder, item: Movie) {
        holder.bind(item)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val tvTitle = view.tvTitle
        private val tvReleaseDate = view.tvReleaseDate
        private val tvDirector = view.tvDirector

        fun bind(movie: Movie) {
            tvTitle.text = movie.title
            tvReleaseDate.text = displayDate(movie.releaseDate)
            tvDirector.text = movie.director
        }
    }
}