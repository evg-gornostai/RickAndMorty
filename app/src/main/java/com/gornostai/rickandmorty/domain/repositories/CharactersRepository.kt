package com.gornostai.rickandmorty.domain.repositories

import com.gornostai.rickandmorty.domain.entities.CharacterEntity

interface CharactersRepository {

    suspend fun loadData()

    suspend fun getCharactersList(): List<CharacterEntity>

    suspend fun getCharacterItem(characterItemId: Int): CharacterEntity

}