package com.gornostai.rickandmorty.domain.mappers

import com.gornostai.rickandmorty.data.network.models.character.CharacterDto
import com.gornostai.rickandmorty.domain.models.CharacterModel
import com.gornostai.rickandmorty.domain.models.Location
import com.gornostai.rickandmorty.domain.models.Origin
import com.gornostai.rickandmorty.utills.UrlToIntParser

class CharacterMapper {

    fun mapDtoToEntity(dto: CharacterDto) = CharacterModel(
        episode = dto.episode.map {
            UrlToIntParser.parseUrlToInt(it)
        },
        gender = dto.gender,
        id = dto.id,
        image = dto.image,
        location = Location(
            name = dto.location.name,
            id = UrlToIntParser.parseUrlToInt(dto.location.url)
        ),
        name = dto.name,
        origin = Origin(
            name = dto.origin.name,
            id = UrlToIntParser.parseUrlToInt(dto.origin.url)
        ),
        species = dto.species,
        status = dto.status,
        type = dto.type
    )

}