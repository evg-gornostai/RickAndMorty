package com.gornostai.rickandmorty.data.repositories

import androidx.lifecycle.LiveData
import com.gornostai.rickandmorty.domain.models.CharacterModel
import com.gornostai.rickandmorty.domain.repositories.CharactersRepository

object CharactersRepositoryImpl: CharactersRepository {

    override fun getCharactersList(): LiveData<List<CharacterModel>> {
        TODO("Not yet implemented")
    }
}