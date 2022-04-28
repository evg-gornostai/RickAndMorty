package com.gornostai.rickandmorty.domain.usecases

import androidx.lifecycle.LiveData
import com.gornostai.rickandmorty.domain.models.CharacterModel
import com.gornostai.rickandmorty.domain.repositories.CharactersRepository

class GetCharactersListUseCase(private val repository: CharactersRepository) {

    fun getCharactersList(): LiveData<List<CharacterModel>>{
        return repository.getCharactersList()
    }

}