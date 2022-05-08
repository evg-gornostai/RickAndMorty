package com.gornostai.rickandmorty.domain.usecases

import com.gornostai.rickandmorty.domain.entities.CharacterEntity
import com.gornostai.rickandmorty.domain.repositories.CharactersRepository

class GetCharactersListUseCase(private val repository: CharactersRepository) {

    suspend fun getCharactersList(): List<CharacterEntity>{
        return repository.getCharactersList()
    }

}