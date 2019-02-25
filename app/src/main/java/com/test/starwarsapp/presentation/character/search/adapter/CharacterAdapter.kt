package com.test.starwarsapp.presentation.character.search.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.test.starwarsapp.R
import com.test.starwarsapp.domain.models.Character
import com.test.starwarsapp.presentation.base.adapter.BaseAdapter
import kotlinx.android.synthetic.main.layout_characters_adapter.view.*
import java.util.*

class CharacterAdapter(characters: MutableList<Character>) :
        BaseAdapter<Character, CharacterAdapter.ViewHolder>(characters) {

    override fun getLayout(): Int {
        return R.layout.layout_characters_adapter
    }

    override fun getHolderView(view: View): ViewHolder {
        return ViewHolder(view)
    }

    override fun bind(holder: ViewHolder, item: Character) {
        holder.bind(item)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val tvName = view.tvName
        private val tvBirth = view.tvBirth

        fun bind(character: Character) {
            tvName.text = character.name
            tvBirth.text = String.format(
                    Locale.getDefault(),
                    itemView.context.getString(R.string.character_search_birth),
                    character.birthYear)
        }
    }
}