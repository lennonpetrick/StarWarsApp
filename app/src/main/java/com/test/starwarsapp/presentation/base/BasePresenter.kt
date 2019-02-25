package com.test.starwarsapp.presentation.base

abstract class BasePresenter<T : BaseContract.View> : BaseContract.Presenter<T> {

    protected var view: T? = null

    override fun view(view: T) {
        this.view = view
    }

    override fun destroy() {
        view = null
    }
}
