package com.gornostai.rickandmorty.domain.mappers

import com.gornostai.rickandmorty.data.network.models.episode.EpisodeDto
import com.gornostai.rickandmorty.domain.models.EpisodeModel
import com.gornostai.rickandmorty.utills.UrlToIntParser

class EpisodeMapper {

    fun mapDtoToEntity(dto: EpisodeDto) = EpisodeModel(
        air_date = dto.air_date,
        characters = dto.characters.map {
            UrlToIntParser.parseUrlToInt(it)
        },
        episode = dto.episode,
        id = dto.id,
        name = dto.name
    )

}