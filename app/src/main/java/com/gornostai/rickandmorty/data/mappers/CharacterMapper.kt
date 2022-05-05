package com.gornostai.rickandmorty.data.mappers

import com.gornostai.rickandmorty.data.network.models.character.CharacterDto
import com.gornostai.rickandmorty.domain.models.CharacterModel
import com.gornostai.rickandmorty.domain.models.Location
import com.gornostai.rickandmorty.domain.models.Origin

class CharacterMapper {

    fun mapDtoToEntity(dto: CharacterDto) = CharacterModel(
        episode = dto.episode,
        gender = dto.gender,
        id = dto.id,
        image = dto.image,
        location = Location(
            name = dto.location.name,
            url = dto.location.url
        ),
        name = dto.name,
        origin = Origin(
            name = dto.origin.name,
            url = dto.origin.url
        ),
        species = dto.species,
        status = dto.status,
        type = dto.type
    )

}