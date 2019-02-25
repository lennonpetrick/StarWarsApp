package com.test.starwarsapp.presentation.character.search

import com.test.starwarsapp.domain.models.Character
import com.test.starwarsapp.domain.usecase.SearchCharacters
import com.test.starwarsapp.presentation.base.BasePresenter
import io.reactivex.observers.DisposableSingleObserver
import org.apache.commons.lang3.StringUtils
import javax.inject.Inject

class CharacterSearchPresenter @Inject constructor(
        private var searchCharacters: SearchCharacters?
) : BasePresenter<CharacterSearchContract.View>(), CharacterSearchContract.Presenter {

    private var search: String? = null

    override fun setSearch(search: String) {
        this.search = search
    }

    override fun load() {
        if (StringUtils.isEmpty(search))
            return

        view?.showLoading()
        searchCharacters?.execute(object : DisposableSingleObserver<List<Character>>() {
            override fun onSuccess(characters: List<Character>) {
                view?.apply {
                    this.hideLoading()
                    this.setCharacters(characters)
                }
            }

            override fun onError(e: Throwable) {
                view?.showError(e.message)
            }
        }, SearchCharacters.Params.forQuery(search!!))
    }

    override fun destroy() {
        searchCharacters = searchCharacters?.let {
            it.dispose()
            null
        }

        super.destroy()
    }

}