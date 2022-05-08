package com.gornostai.rickandmorty.data.mappers

import com.gornostai.rickandmorty.data.local.models.EpisodeDbModel
import com.gornostai.rickandmorty.data.remote.models.episode.EpisodeDto
import com.gornostai.rickandmorty.domain.entities.EpisodeEntity
import com.gornostai.rickandmorty.utills.UrlToIdParser

class EpisodeMapper {

    fun mapDtoToDbModel(dto: EpisodeDto): EpisodeDbModel = EpisodeDbModel(
        id = dto.id,
        air_date = dto.air_date,
        characters = dto.characters.map { UrlToIdParser.parseUrlToStringId(it) },
        created = dto.created,
        episode = dto.episode,
        name = dto.name
    )

    fun mapDbModelToEntity(dbModel: EpisodeDbModel) = EpisodeEntity(
        air_date = dbModel.air_date,
        characters = dbModel.characters,
        created = dbModel.created,
        episode = dbModel.episode,
        id = dbModel.id,
        name = dbModel.name
    )

}