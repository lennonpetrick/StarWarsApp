package com.test.starwarsapp.presentation.home

import com.test.starwarsapp.domain.models.Movie
import com.test.starwarsapp.domain.usecase.GetMovies
import com.test.starwarsapp.presentation.base.BasePresenter
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class HomePresenter @Inject constructor(
        private var getMovies: GetMovies?
) : BasePresenter<HomeContract.View>(), HomeContract.Presenter {

    override fun load() {
        view?.showLoading()
        getMovies?.execute(object : DisposableSingleObserver<List<Movie>>() {
            override fun onSuccess(movies: List<Movie>) {
                view?.apply {
                    this.hideLoading()
                    this.setMovies(movies)
                }
            }

            override fun onError(e: Throwable) {
                view?.showError(e.message)
            }
        }, null)
    }

    override fun destroy() {
        getMovies = getMovies?.let {
            it.dispose()
            null
        }

        super.destroy()
    }
}