package com.test.starwarsapp.di.modules

import com.test.starwarsapp.presentation.CharacterDetailActivity
import com.test.starwarsapp.presentation.CharacterSearchActivity
import com.test.starwarsapp.presentation.HomeActivity
import com.test.starwarsapp.presentation.MovieDetailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun homeActivity(): HomeActivity

    @ContributesAndroidInjector
    abstract fun movieDetailActivity(): MovieDetailActivity

    @ContributesAndroidInjector
    abstract fun characterSearchActivity(): CharacterSearchActivity

    @ContributesAndroidInjector
    abstract fun characterDetailActivity(): CharacterDetailActivity
}