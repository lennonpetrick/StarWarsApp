package com.test.starwarsapp.di.modules

import com.test.starwarsapp.data.repository.RepositoryImpl
import com.test.starwarsapp.domain.Repository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    internal abstract fun providesRepository(repository: RepositoryImpl): Repository

}