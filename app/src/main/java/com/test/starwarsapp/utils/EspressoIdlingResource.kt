package com.test.starwarsapp.utils

import android.support.test.espresso.IdlingResource
import java.util.concurrent.atomic.AtomicInteger

object EspressoIdlingResource : IdlingResource {

    private val counter = AtomicInteger(0)
    @Volatile private var resourceCallback: IdlingResource.ResourceCallback? = null

    fun increment() {
        counter.incrementAndGet()
    }

    fun decrement() {
        if (counter.get() <= 0)
            return

        if (counter.decrementAndGet() == 0) {
            resourceCallback?.onTransitionToIdle()
        }
    }

    override fun getName(): String {
        return EspressoIdlingResource::class.java.name
    }

    override fun isIdleNow(): Boolean {
        return counter.get() == 0
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        resourceCallback = callback
    }
}