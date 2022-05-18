package com.gornostai.rickandmorty.presentation.screens.characters

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gornostai.rickandmorty.domain.entities.CharacterEntity
import com.gornostai.rickandmorty.domain.entities.CharacterFilterEntity
import com.gornostai.rickandmorty.domain.usecases.GetCharactersListUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

val FAKE_CHARACTER_ENTITY = CharacterEntity(
    id = 1,
    name = "fake name",
    created = "fake created",
    episode = listOf(),
    gender = "fake gender",
    image = "fake image",
    locationName = "fake location name",
    locationId = "fake location id",
    originId = "fake origin id",
    originName = "fake origin name",
    species = "fake species",
    status = "fake status",
    type = "fake type"
)

class CharactersViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    private val dispatcher = StandardTestDispatcher()

    private val fakeFilter = CharacterFilterEntity()
    private val getCharactersListUseCase = mockk<GetCharactersListUseCase>()
    private val expected = listOf(
        FAKE_CHARACTER_ENTITY,
        FAKE_CHARACTER_ENTITY.copy(id = 2),
        FAKE_CHARACTER_ENTITY.copy(id = 3),
    )

    @ExperimentalCoroutinesApi
    @Before
    fun init() {
        Dispatchers.setMain(dispatcher)
        coEvery { getCharactersListUseCase.getCharactersList(any()) } returns listOf(
            FAKE_CHARACTER_ENTITY,
            FAKE_CHARACTER_ENTITY.copy(id = 2),
            FAKE_CHARACTER_ENTITY.copy(id = 3),
        )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun should_fetch_data_and_return_it() {
        val viewModel = CharactersViewModel(getCharactersListUseCase)
        runTest {
            viewModel.fetchData(fakeFilter)
        }
        val actual = viewModel.charactersList.value
        assertEquals(expected, actual)
    }

    @ExperimentalCoroutinesApi
    @After
    fun after() {
        Dispatchers.resetMain()
    }

}
