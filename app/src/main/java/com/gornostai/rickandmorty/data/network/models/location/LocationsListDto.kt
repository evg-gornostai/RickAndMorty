package com.gornostai.rickandmorty.data.network.models.location

data class LocationsListDto(
    val info: Info,
    val results: List<LocationDto>
)