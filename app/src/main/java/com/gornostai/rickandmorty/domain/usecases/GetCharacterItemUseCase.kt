package com.gornostai.rickandmorty.domain.usecases

import com.gornostai.rickandmorty.domain.models.CharacterModel
import com.gornostai.rickandmorty.domain.repositories.CharactersRepository

class GetCharacterItemUseCase(private val repository: CharactersRepository) {

    fun getCharacterItem(characterItemId: Int): CharacterModel{
        return repository.getCharacterItem(characterItemId)
    }

}