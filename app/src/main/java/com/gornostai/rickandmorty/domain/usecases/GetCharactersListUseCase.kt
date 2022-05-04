package com.gornostai.rickandmorty.domain.usecases

import androidx.lifecycle.LiveData
import com.gornostai.rickandmorty.domain.models.CharacterModel
import com.gornostai.rickandmorty.domain.repositories.CharactersRepository

class GetCharactersListUseCase(private val repository: CharactersRepository) {

    suspend fun getCharactersList(): List<CharacterModel>{
        return repository.getCharactersList()
    }

}