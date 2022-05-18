package com.gornostai.rickandmorty.domain.usecases

import com.gornostai.rickandmorty.domain.entities.CharacterEntity
import com.gornostai.rickandmorty.domain.repositories.RickAndMortyApiRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Ignore
import org.junit.Test

const val FAKE_CHARACTER_ID = 1

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

class GetCharacterItemUseCaseTest {

    var actual: CharacterEntity? = null
    lateinit var expected: CharacterEntity
    lateinit var repository: RickAndMortyApiRepository
    lateinit var useCase: GetCharacterItemUseCase

    @Before
    fun init() {
        expected = FAKE_CHARACTER_ENTITY
        repository = mockk {
            coEvery { getCharacterItem(FAKE_CHARACTER_ID) } returns FAKE_CHARACTER_ENTITY
        }
        useCase = GetCharacterItemUseCase(repository)
    }

    @Test
    fun should_return_the_same_data_as_in_repository() {
        runBlocking {
            actual = useCase.getCharacterItem(FAKE_CHARACTER_ID)
        }
        assertEquals(expected, actual)
    }

}