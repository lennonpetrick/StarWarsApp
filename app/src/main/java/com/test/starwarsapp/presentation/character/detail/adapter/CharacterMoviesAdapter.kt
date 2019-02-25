package com.test.starwarsapp.presentation.character.detail.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.test.starwarsapp.R
import com.test.starwarsapp.domain.models.Movie
import com.test.starwarsapp.presentation.base.adapter.BaseAdapter
import kotlinx.android.synthetic.main.layout_character_movies_adapter.view.*
import org.apache.commons.lang3.StringUtils

class CharacterMoviesAdapter(movies: MutableList<Movie>) :
        BaseAdapter<Movie, CharacterMoviesAdapter.ViewHolder>(movies) {

    override fun getLayout(): Int {
        return R.layout.layout_character_movies_adapter
    }

    override fun getHolderView(view: View): ViewHolder {
        return ViewHolder(view)
    }

    override fun bind(holder: ViewHolder, item: Movie) {
        holder.bind(item)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val tvTitle = view.tvTitle
        private val tvOpeningCrawl = view.tvOpeningCrawl

        fun bind(movie: Movie) {
            tvTitle.text = movie.title
            tvOpeningCrawl.text = normalizeOpeningCrawl(movie.openingCrawl)
        }

        private fun normalizeOpeningCrawl(text: String): String {
            return StringUtils.normalizeSpace(text)
        }
    }
}