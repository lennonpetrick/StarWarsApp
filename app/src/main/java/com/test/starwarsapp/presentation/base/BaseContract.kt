package com.test.starwarsapp.presentation.base

interface BaseContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showError(message: String?)
    }

    interface Presenter<T: View> {
        fun view(view: T)
        fun load()
        fun destroy()
    }

}
