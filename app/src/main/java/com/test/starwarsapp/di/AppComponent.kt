package com.test.starwarsapp.di

import com.test.starwarsapp.MainApplication
import com.test.starwarsapp.di.modules.ActivityModule
import com.test.starwarsapp.di.modules.NetworkModule
import com.test.starwarsapp.di.modules.RepositoryModule
import com.test.starwarsapp.di.modules.UseCaseModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
        AndroidSupportInjectionModule::class,
        ActivityModule::class,
        UseCaseModule::class,
        RepositoryModule::class,
        NetworkModule::class
])
interface AppComponent {
    fun inject(application: MainApplication)
}
