package com.gornostai.rickandmorty.domain.usecases

import com.gornostai.rickandmorty.domain.entities.CharacterEntity
import com.gornostai.rickandmorty.domain.repositories.CharactersRepository
import javax.inject.Inject

class GetCharactersListUseCase @Inject constructor(
    private val repository: CharactersRepository
) {

    suspend fun getCharactersList(): List<CharacterEntity> {
        return repository.getCharactersList()
    }

}