package com.gornostai.rickandmorty.domain.mappers

import com.gornostai.rickandmorty.data.network.models.location.LocationDto
import com.gornostai.rickandmorty.domain.models.LocationModel
import com.gornostai.rickandmorty.utills.UrlToIntParser

class LocationMapper {

    fun mapDtoToEntity(dto: LocationDto) = LocationModel(
        dimension = dto.dimension,
        id = dto.id,
        name = dto.name,
        residents = dto.residents.map {
            UrlToIntParser.parseUrlToInt(it)
        },
        type = dto.type
    )

}