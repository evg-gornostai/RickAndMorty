package com.gornostai.rickandmorty.domain.repositories

import com.gornostai.rickandmorty.domain.entities.CharacterEntity
import com.gornostai.rickandmorty.domain.entities.CharacterFilterEntity

interface CharactersRepository {

    suspend fun getCharactersList(): List<CharacterEntity>

    suspend fun getCharacterItem(characterItemId: Int): CharacterEntity

    suspend fun getFilteredCharactersList(filter: CharacterFilterEntity): List<CharacterEntity>

}