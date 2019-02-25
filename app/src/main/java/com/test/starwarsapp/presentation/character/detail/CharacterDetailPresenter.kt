package com.test.starwarsapp.presentation.character.detail

import com.test.starwarsapp.domain.models.Character
import com.test.starwarsapp.domain.usecase.GetCharacter
import com.test.starwarsapp.presentation.base.BasePresenter
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class CharacterDetailPresenter @Inject constructor(
        private var getCharacter: GetCharacter?
) : BasePresenter<CharacterDetailContract.View>(), CharacterDetailContract.Presenter {

    private var characterUrl: String? = null

    override fun setCharacterUrl(url: String) {
        characterUrl = url
    }

    override fun load() {
        view?.apply {
            this.showLoading()
            this.setContentVisibility(false)
        }

        getCharacter?.execute(object : DisposableSingleObserver<Character>() {
            override fun onSuccess(character: Character) {
                view?.apply {
                    this.hideLoading()
                    this.setContentVisibility(true)

                    this.setName(character.name)
                    this.setBirth(character.birthYear)
                    this.setHeight(character.height)

                    character.planet?.also {
                        this.setPlanet(it.name)
                    }

                    character.species?.also { species ->
                        val specie = species[0]
                        this.setSpecies(specie.name)
                        this.setSpecieLanguage(specie.language)
                        specie.planet?.also {
                            this.setSpeciePlanet(it.name)
                            this.setSpeciePlanetPopulation(it.population)
                        }
                    }

                    character.films?.also {
                        this.setMovies(it)
                    }
                }
            }

            override fun onError(e: Throwable) {
                view?.showError(e.message)
            }
        }, GetCharacter.Params.forUrl(characterUrl!!))
    }

    override fun destroy() {
        getCharacter = getCharacter?.let {
            it.dispose()
            null
        }

        super.destroy()
    }
}