package com.gornostai.rickandmorty.domain.usecases

import com.gornostai.rickandmorty.domain.entities.CharacterEntity
import com.gornostai.rickandmorty.domain.entities.CharacterFilterEntity
import com.gornostai.rickandmorty.domain.repositories.RickAndMortyApiRepository
import javax.inject.Inject

class GetCharactersListUseCase @Inject constructor(
    private val repository: RickAndMortyApiRepository
) {

    suspend fun getCharactersList(filter: CharacterFilterEntity): List<CharacterEntity> {
        return repository.getCharactersList(filter)
    }

    suspend fun getCharactersListByIds(arrayOfId: List<String>): List<CharacterEntity> {
        return repository.getCharactersListByIds(arrayOfId)
    }

}