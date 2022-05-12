package com.gornostai.rickandmorty.di

import android.app.Application
import com.gornostai.rickandmorty.data.local.AppDataBase
import com.gornostai.rickandmorty.data.local.dao.CharactersDao
import com.gornostai.rickandmorty.data.local.dao.EpisodesDao
import com.gornostai.rickandmorty.data.local.dao.LocationsDao
import com.gornostai.rickandmorty.data.remote.ApiFactory
import com.gornostai.rickandmorty.data.remote.services.CharactersService
import com.gornostai.rickandmorty.data.remote.services.EpisodesService
import com.gornostai.rickandmorty.data.remote.services.LocationsService
import com.gornostai.rickandmorty.data.repositories.CharactersRepositoryImpl
import com.gornostai.rickandmorty.data.repositories.EpisodesRepositoryImpl
import com.gornostai.rickandmorty.data.repositories.LocationsRepositoryImpl
import com.gornostai.rickandmorty.domain.repositories.CharactersRepository
import com.gornostai.rickandmorty.domain.repositories.EpisodesRepository
import com.gornostai.rickandmorty.domain.repositories.LocationsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun bindCharactersRepository(impl: CharactersRepositoryImpl): CharactersRepository

    @Binds
    @ApplicationScope
    fun bindEpisodesRepository(impl: EpisodesRepositoryImpl): EpisodesRepository

    @Binds
    @ApplicationScope
    fun bindLocationsRepository(impl: LocationsRepositoryImpl): LocationsRepository

    companion object {

        @Provides
        @ApplicationScope
        fun provideCharacterDao(
            application: Application
        ): CharactersDao {
            return AppDataBase.getInstance(application).characterDao()
        }

        @Provides
        @ApplicationScope
        fun provideEpisodeDao(
            application: Application
        ): EpisodesDao {
            return AppDataBase.getInstance(application).episodeDao()
        }

        @Provides
        @ApplicationScope
        fun provideLocationDao(
            application: Application
        ): LocationsDao {
            return AppDataBase.getInstance(application).locationDao()
        }

        @Provides
        @ApplicationScope
        fun provideCharactersService(): CharactersService {
            return ApiFactory.characterService
        }

        @Provides
        @ApplicationScope
        fun provideEpisodesService(): EpisodesService {
            return ApiFactory.episodeService
        }

        @Provides
        @ApplicationScope
        fun provideLocationsService(): LocationsService {
            return ApiFactory.locationService
        }

    }

}