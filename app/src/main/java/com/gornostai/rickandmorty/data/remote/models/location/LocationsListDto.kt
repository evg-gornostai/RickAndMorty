package com.gornostai.rickandmorty.data.remote.models.location

data class LocationsListDto(
    val info: Info,
    val results: List<LocationDto>
)