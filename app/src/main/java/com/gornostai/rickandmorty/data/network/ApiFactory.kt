package com.gornostai.rickandmorty.data.network

import com.gornostai.rickandmorty.data.network.services.CharacterService
import com.gornostai.rickandmorty.data.network.services.EpisodeService
import com.gornostai.rickandmorty.data.network.services.LocationService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {

    private const val BASE_URL = "https://rickandmortyapi.com/api/"

    private fun loggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    val characterService = retrofit.create(CharacterService::class.java)
    val locationService = retrofit.create(LocationService::class.java)
    val episodeService = retrofit.create(EpisodeService::class.java)

}