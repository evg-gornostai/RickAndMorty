package com.gornostai.rickandmorty.domain.repositories

import com.gornostai.rickandmorty.domain.models.CharacterModel

interface CharactersRepository {

    suspend fun getCharactersList(): List<CharacterModel>

    suspend fun getCharacterItem(characterItemId: Int): CharacterModel

}