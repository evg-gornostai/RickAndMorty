package com.gornostai.rickandmorty.data.remote.services

import com.gornostai.rickandmorty.data.remote.models.location.LocationDto
import com.gornostai.rickandmorty.data.remote.models.location.LocationsListDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LocationsService {

    @GET("location/")
    suspend fun getLocationsList(
        @Query("page") page: Int = 1
    ): LocationsListDto

    @GET("location/")
    suspend fun getFilteredLocationsList(
        @Query("name") name: String = "",
        @Query("type") type: String = "",
        @Query("dimension") dimension: String = ""
    ): Response<LocationsListDto>

    @GET("location/{id}")
    suspend fun getLocation(
        @Path("id") id: String
    ): LocationDto

}