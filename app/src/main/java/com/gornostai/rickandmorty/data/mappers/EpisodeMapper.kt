package com.gornostai.rickandmorty.data.mappers

import com.gornostai.rickandmorty.data.network.models.episode.EpisodeDto
import com.gornostai.rickandmorty.domain.models.EpisodeModel

class EpisodeMapper {

    fun mapDtoToEntity(dto: EpisodeDto) = EpisodeModel(
        air_date = dto.air_date,
        characters = dto.characters,
        episode = dto.episode,
        id = dto.id,
        name = dto.name
    )

}