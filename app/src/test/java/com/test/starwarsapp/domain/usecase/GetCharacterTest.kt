package com.test.starwarsapp.domain.usecase

import com.google.gson.Gson
import com.test.starwarsapp.data.entities.CharacterEntity
import com.test.starwarsapp.data.entities.MovieEntity
import com.test.starwarsapp.data.entities.PlanetEntity
import com.test.starwarsapp.data.entities.SpecieEntity
import com.test.starwarsapp.domain.Repository
import com.test.starwarsapp.domain.models.Character
import com.test.starwarsapp.domain.models.Movie
import com.test.starwarsapp.domain.models.Planet
import com.test.starwarsapp.domain.models.Specie
import com.test.starwarsapp.filmJsonMock
import com.test.starwarsapp.peopleJsonMock
import com.test.starwarsapp.planetJsonMock
import com.test.starwarsapp.specieJsonMock
import com.test.starwarsapp.utils.dateToString
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetCharacterTest {

    private val scheduler: Scheduler = Schedulers.trampoline()

    private lateinit var characterMock: CharacterEntity
    private lateinit var filmMock: MovieEntity
    private lateinit var specieMock: SpecieEntity
    private lateinit var planetMock: PlanetEntity

    @Mock
    private lateinit var repository: Repository

    @Before
    fun setUp() {
        val gson = Gson()
        mockCharacter(gson)
        mockMovie(gson)
        mockSpecie(gson)
        mockPlanet(gson)
    }

    private fun mockCharacter(gson: Gson) {
        characterMock = gson.fromJson(peopleJsonMock(), CharacterEntity::class.java)
        `when`(repository.getCharacter(anyInt())).thenReturn(Single.just(characterMock))

    }

    private fun mockMovie(gson: Gson) {
        filmMock = gson.fromJson(filmJsonMock(), MovieEntity::class.java)
        `when`(repository.getMovie(anyInt())).thenReturn(Single.just(filmMock))
    }

    private fun mockSpecie(gson: Gson) {
        specieMock = gson.fromJson(specieJsonMock(), SpecieEntity::class.java)
        `when`(repository.getSpecies(anyInt())).thenReturn(Single.just(specieMock))

    }

    private fun mockPlanet(gson: Gson) {
        planetMock = gson.fromJson(planetJsonMock(), PlanetEntity::class.java)
        `when`(repository.getPlanet(anyInt())).thenReturn(Single.just(planetMock))
    }

    @Test
    fun `get character`() {
        val params = Mockito.mock(GetCharacter.Params::class.java)
        `when`(params.id).thenReturn(1)

        val useCase = GetCharacter(repository, scheduler, scheduler, CompositeDisposable())

        useCase.execute(object : DisposableSingleObserver<Character>() {
            override fun onSuccess(character: Character) {
                assertCharacter(character)
                assertPlanet(character.planet!!)
                assertSpecie(character.species!![0])
                assertMovie(character.films!![0])
            }

            override fun onError(e: Throwable) {}
        }, params)
    }

    private fun assertCharacter(character: Character) {
        assertThat(character.name, equalTo(characterMock.name))
        assertThat(character.birthYear, equalTo(characterMock.birthYear))
        assertThat(character.height, equalTo(characterMock.height))
    }

    private fun assertMovie(movie: Movie) {
        assertThat(movie.title, equalTo(filmMock.title))
        assertThat(movie.episodeId, equalTo(filmMock.episodeId))
        assertThat(movie.director, equalTo(filmMock.director))
        assertThat(movie.producer, equalTo(filmMock.producer))
        assertThat(dateToString(movie.releaseDate), equalTo(filmMock.releaseDate))
        assertThat(movie.openingCrawl, equalTo(filmMock.openingCrawl))
    }

    private fun assertSpecie(specie: Specie) {
        assertThat(specie.name, equalTo(specie.name))
        assertThat(specie.language, equalTo(specie.language))
        assertPlanet(specie.planet!!)
    }

    private fun assertPlanet(planet: Planet) {
        assertThat(planet.name, equalTo(planetMock.name))
        assertThat(planet.population, equalTo(planetMock.population))
    }
}