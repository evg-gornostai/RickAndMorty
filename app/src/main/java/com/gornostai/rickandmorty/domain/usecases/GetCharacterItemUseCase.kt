package com.gornostai.rickandmorty.domain.usecases

import com.gornostai.rickandmorty.domain.entities.CharacterEntity
import com.gornostai.rickandmorty.domain.repositories.RickAndMortyApiRepository
import javax.inject.Inject

class GetCharacterItemUseCase @Inject constructor(
    private val repository: RickAndMortyApiRepository
) {

    suspend fun getCharacterItem(characterItemId: Int): CharacterEntity? {
        return repository.getCharacterItem(characterItemId)
    }

}