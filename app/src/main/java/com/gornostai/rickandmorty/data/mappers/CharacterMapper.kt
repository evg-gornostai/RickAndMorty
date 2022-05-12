package com.gornostai.rickandmorty.data.mappers

import com.gornostai.rickandmorty.data.local.models.CharacterDbModel
import com.gornostai.rickandmorty.data.remote.models.character.CharacterDto
import com.gornostai.rickandmorty.domain.entities.CharacterEntity
import com.gornostai.rickandmorty.utils.UrlToIdParser

class CharacterMapper {

    fun mapDtoToDbModel(dto: CharacterDto) = CharacterDbModel(
        episode = dto.episode.map { UrlToIdParser.parseUrlToStringId(it) },
        gender = dto.gender,
        id = dto.id,
        image = dto.image,
        locationName = dto.location.name,
        locationId = UrlToIdParser.parseUrlToStringId(dto.location.url),
        name = dto.name,
        originName = dto.origin.name,
        originId = UrlToIdParser.parseUrlToStringId(dto.origin.url),
        species = dto.species,
        status = dto.status,
        type = dto.type,
        created = dto.created
    )

    fun mapDbModelToEntity(dbModel: CharacterDbModel) = CharacterEntity(
        created = dbModel.created,
        episode = dbModel.episode,
        gender = dbModel.gender,
        id = dbModel.id,
        image = dbModel.image,
        locationName = dbModel.locationName,
        locationId = dbModel.locationId,
        name = dbModel.name,
        originName = dbModel.originName,
        originId = dbModel.originId,
        species = dbModel.species,
        status = dbModel.status,
        type = dbModel.type
    )

}