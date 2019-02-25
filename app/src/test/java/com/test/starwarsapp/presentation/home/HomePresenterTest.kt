package com.test.starwarsapp.presentation.home

import com.google.gson.Gson
import com.test.starwarsapp.data.entities.MovieEntity
import com.test.starwarsapp.domain.Repository
import com.test.starwarsapp.domain.usecase.GetMovies
import com.test.starwarsapp.filmJsonMock
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomePresenterTest {

    private lateinit var presenter: HomeContract.Presenter

    @Mock
    private lateinit var view: HomeContract.View

    @Mock
    private lateinit var repository: Repository

    @Before
    fun setUp() {
        val scheduler = Schedulers.trampoline()

        val movieMock = Gson().fromJson(filmJsonMock(), MovieEntity::class.java)
        `when`(repository.getMovies()).thenReturn(Single.just(listOf(movieMock)))

        val useCase = GetMovies(repository, scheduler, scheduler, CompositeDisposable())

        presenter = HomePresenter(useCase)
        presenter.view(view)
    }

    @Test
    fun `load movies`() {
        presenter.load()

        verify(view).showLoading()
        verify(view).hideLoading()
        verify(view).setMovies(ArgumentMatchers.anyList())
    }
}