package com.test.starwarsapp.di.modules

import com.test.starwarsapp.presentation.character.search.CharacterSearchContract
import com.test.starwarsapp.presentation.character.search.CharacterSearchPresenter
import com.test.starwarsapp.presentation.home.HomeContract
import com.test.starwarsapp.presentation.home.HomePresenter
import com.test.starwarsapp.presentation.moviedetail.MovieDetailContract
import com.test.starwarsapp.presentation.moviedetail.MovieDetailPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class PresenterModule {
    @Binds
    internal abstract fun providesHomePresenter(presenter: HomePresenter): HomeContract.Presenter

    @Binds
    internal abstract fun providesMovieDetailPresenter(presenter: MovieDetailPresenter): MovieDetailContract.Presenter

    @Binds
    internal abstract fun providesCharacterSearchPresenter(presenter: CharacterSearchPresenter): CharacterSearchContract.Presenter
}
