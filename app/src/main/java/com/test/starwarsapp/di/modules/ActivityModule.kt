package com.test.starwarsapp.di.modules

import com.test.starwarsapp.presentation.character.detail.CharacterDetailActivity
import com.test.starwarsapp.presentation.character.search.CharacterSearchActivity
import com.test.starwarsapp.presentation.home.HomeActivity
import com.test.starwarsapp.presentation.moviedetail.MovieDetailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [PresenterModule::class])
    abstract fun homeActivity(): HomeActivity

    @ContributesAndroidInjector(modules = [PresenterModule::class])
    abstract fun movieDetailActivity(): MovieDetailActivity

    @ContributesAndroidInjector(modules = [PresenterModule::class])
    abstract fun characterSearchActivity(): CharacterSearchActivity

    @ContributesAndroidInjector(modules = [PresenterModule::class])
    abstract fun characterDetailActivity(): CharacterDetailActivity
}