package com.test.starwarsapp.presentation.character.search

import com.test.starwarsapp.domain.models.Character
import com.test.starwarsapp.presentation.base.BaseContract

interface CharacterSearchContract {
    interface View : BaseContract.View {
        fun setCharacters(characters: List<Character>)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun setSearch(search: String)
    }
}