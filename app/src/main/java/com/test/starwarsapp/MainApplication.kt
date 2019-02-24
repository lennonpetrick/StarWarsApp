package com.test.starwarsapp

import android.app.Activity
import android.app.Application
import com.test.starwarsapp.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class MainApplication : Application(), HasActivityInjector {

    @Inject lateinit var androidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        buildDependencyInjection()
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return androidInjector
    }

    private fun buildDependencyInjection() {
        DaggerAppComponent.create().inject(this)
    }
}