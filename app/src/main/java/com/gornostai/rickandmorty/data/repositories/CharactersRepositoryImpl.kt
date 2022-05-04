package com.gornostai.rickandmorty.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gornostai.rickandmorty.domain.models.CharacterModel
import com.gornostai.rickandmorty.domain.repositories.CharactersRepository

object CharactersRepositoryImpl : CharactersRepository {

    private val characterList = mutableListOf<CharacterModel>()

    init {
        for (i in 0..50) {
            characterList.add(
                CharacterModel(
                    id = i,
                    name = "rick morty$i",
                    species = "human$i",
                    status = "alive$i",
                    gender = "man$i",
                    image = ""
                )
            )
        }
    }

    override suspend fun getCharactersList(): List<CharacterModel> {
        return characterList.toList()
    }

    override suspend fun getCharacterItem(characterItemId: Int): CharacterModel {
        return characterList.find {
            it.id == characterItemId
        } ?: throw RuntimeException("Element with id $characterItemId not found")
    }
}