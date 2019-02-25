package com.test.starwarsapp

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.test.starwarsapp.presentation.home.HomeActivity
import com.test.starwarsapp.presentation.moviedetail.MovieDetailActivity
import com.test.starwarsapp.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieDetailTest {

    @Rule
    @JvmField
    var activityRule = IntentsTestRule(HomeActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry
                .getInstance()
                .register(EspressoIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry
                .getInstance()
                .unregister(EspressoIdlingResource)
    }

    @Test
    fun seeMovieDetail() {
        onView(withId(R.id.rvMovies))
                .perform(RecyclerViewActions
                        .actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        intended(hasComponent(MovieDetailActivity::class.java.name))
    }

}