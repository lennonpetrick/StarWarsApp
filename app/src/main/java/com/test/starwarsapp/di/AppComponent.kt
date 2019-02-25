package com.test.starwarsapp.di

import com.test.starwarsapp.MainApplication
import com.test.starwarsapp.di.modules.*
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
        AndroidSupportInjectionModule::class,
        ActivityModule::class,
        PresenterModule::class,
        UseCaseModule::class,
        RepositoryModule::class,
        NetworkModule::class
])
interface AppComponent {
    fun inject(application: MainApplication)
}
