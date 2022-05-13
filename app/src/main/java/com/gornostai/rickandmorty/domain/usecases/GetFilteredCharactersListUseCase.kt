package com.gornostai.rickandmorty.domain.usecases

import com.gornostai.rickandmorty.domain.entities.CharacterEntity
import com.gornostai.rickandmorty.domain.entities.CharacterFilterEntity
import com.gornostai.rickandmorty.domain.repositories.CharactersRepository
import javax.inject.Inject

class GetFilteredCharactersListUseCase @Inject constructor(
    private val repository: CharactersRepository
) {

    suspend fun getFilteredCharactersList(filter: CharacterFilterEntity): List<CharacterEntity> {
        return repository.getFilteredCharactersList(filter)
    }

}