package com.test.starwarsapp.domain.usecase

import com.test.starwarsapp.di.IOScheduler
import com.test.starwarsapp.di.UIScheduler
import com.test.starwarsapp.utils.EspressoIdlingResource
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver


/**
 * This abstract class is an UseCase which represents an execution unit
 * for different cases.
 *
 */
abstract class UseCase<T, P>(
        @IOScheduler private val workThread: Scheduler,
        @UIScheduler private val uiThread: Scheduler,
        private val disposables: CompositeDisposable
) {

    /**
     * Returns an [Single] which will be used when executing the current [UseCase].
     *
     * @param params The parameters to execute the [UseCase].
     */
    protected abstract fun buildUseCase(params: P): Single<T>

    /**
     * Disposes all observers.
     */
    fun dispose() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }

    /**
     * Adds a disposable into [CompositeDisposable].
     *
     * @param disposable A [Disposable] from an observer.
     */
    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    /**
     * Runs the current [UseCase].
     *
     * @param observer An observer which will be called after it gets executed.
     * @param params The parameters to execute the current [UseCase].
     */
    fun execute(observer: DisposableSingleObserver<T>, params: P) {
        buildUseCase(params)
                .doOnSubscribe { disposable ->
                    addDisposable(disposable)
                    EspressoIdlingResource.increment()
                }
                .doFinally { EspressoIdlingResource.decrement() }
                .subscribeOn(workThread)
                .observeOn(uiThread)
                .subscribe(observer)
    }

}