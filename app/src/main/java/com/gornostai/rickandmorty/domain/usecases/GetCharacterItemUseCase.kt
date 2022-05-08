package com.gornostai.rickandmorty.domain.usecases

import com.gornostai.rickandmorty.domain.entities.CharacterEntity
import com.gornostai.rickandmorty.domain.repositories.CharactersRepository

class GetCharacterItemUseCase(private val repository: CharactersRepository) {

    suspend fun getCharacterItem(characterItemId: Int): CharacterEntity{
        return repository.getCharacterItem(characterItemId)
    }

}