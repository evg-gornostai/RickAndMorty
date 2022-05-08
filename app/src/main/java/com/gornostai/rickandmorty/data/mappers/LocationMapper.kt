package com.gornostai.rickandmorty.data.mappers

import com.gornostai.rickandmorty.data.local.models.LocationDbModel
import com.gornostai.rickandmorty.data.remote.models.location.LocationDto
import com.gornostai.rickandmorty.domain.entities.LocationEntity
import com.gornostai.rickandmorty.utills.UrlToIdParser

class LocationMapper {

    fun mapDtoToDbModel(dto: LocationDto) = LocationDbModel(
        id = dto.id,
        created = dto.created,
        dimension = dto.dimension,
        name = dto.name,
        residents = dto.residents.map { UrlToIdParser.parseUrlToStringId(it) },
        type = dto.type
    )

    fun mapDbModelToEntity(dbModel: LocationDbModel) = LocationEntity(
        created = dbModel.created,
        dimension = dbModel.dimension,
        id = dbModel.id,
        name = dbModel.name,
        residents = dbModel.residents,
        type = dbModel.type
    )

}