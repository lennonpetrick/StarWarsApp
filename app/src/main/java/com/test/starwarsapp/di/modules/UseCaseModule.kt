package com.test.starwarsapp.di.modules

import com.test.starwarsapp.di.qualifiers.IOScheduler
import com.test.starwarsapp.di.qualifiers.UIScheduler
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Provides
    @Singleton
    @IOScheduler
    internal fun ioScheduler(): Scheduler {
        return Schedulers.io()
    }

    @Provides
    @Singleton
    @UIScheduler
    internal fun uiScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    @Provides
    internal fun compositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

}