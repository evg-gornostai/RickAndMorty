package com.gornostai.rickandmorty.data.mappers

import com.gornostai.rickandmorty.data.network.models.location.LocationDto
import com.gornostai.rickandmorty.domain.models.LocationModel

class LocationMapper {

    fun mapDtoToEntity(dto: LocationDto) = LocationModel(
        dimension = dto.dimension,
        id = dto.id,
        name = dto.name,
        residents = dto.residents,
        type = dto.type
    )

}