package com.gornostai.rickandmorty.data.network.services

import com.gornostai.rickandmorty.data.network.models.location.LocationDto
import com.gornostai.rickandmorty.data.network.models.location.LocationsListDto
import retrofit2.http.GET
import retrofit2.http.Path

interface LocationService {

    @GET("location")
    suspend fun getLocationsList(): LocationsListDto

    @GET("location/{id}")
    suspend fun getLocation(
        @Path("id") id: String
    ): LocationDto

}