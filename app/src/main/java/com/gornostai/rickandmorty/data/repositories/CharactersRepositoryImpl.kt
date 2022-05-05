package com.gornostai.rickandmorty.data.repositories

import com.gornostai.rickandmorty.data.mappers.CharacterMapper
import com.gornostai.rickandmorty.data.network.ApiFactory
import com.gornostai.rickandmorty.domain.models.CharacterModel
import com.gornostai.rickandmorty.domain.repositories.CharactersRepository

class CharactersRepositoryImpl : CharactersRepository {

    override suspend fun getCharactersList(): List<CharacterModel> {
        return ApiFactory.characterService.getCharactersList().results.map {
            CharacterMapper().mapDtoToEntity(it)
        }
    }

    override suspend fun getCharacterItem(characterItemId: Int): CharacterModel {
        val dtoModel = ApiFactory.characterService.getCharacter(characterItemId.toString())
        return CharacterMapper().mapDtoToEntity(dtoModel)
    }
}