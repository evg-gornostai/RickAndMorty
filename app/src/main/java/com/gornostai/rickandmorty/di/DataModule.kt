package com.gornostai.rickandmorty.di

import android.app.Application
import com.gornostai.rickandmorty.data.datasources.*
import com.gornostai.rickandmorty.data.local.AppDataBase
import com.gornostai.rickandmorty.data.local.dao.RickAndMortyApiDao
import com.gornostai.rickandmorty.data.remote.ApiFactory
import com.gornostai.rickandmorty.data.remote.services.RickAndMortyApiService
import com.gornostai.rickandmorty.data.repositories.RickAndMortyApiRepositoryImpl
import com.gornostai.rickandmorty.domain.repositories.RickAndMortyApiRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    fun bindRickAndMortyApiRepository(impl: RickAndMortyApiRepositoryImpl): RickAndMortyApiRepository

    @Binds
    fun bindIsOnlineDataSource(impl: IsOnlineDataSourceImpl): IsOnlineDataSource

    @Binds
    fun bindLocalDataSource(impl: LocalDataSourceImpl): LocalDataSource

    @Binds
    fun bindRemoteDataSource(impl: RemoteDataSourceImpl): RemoteDataSource

    companion object {

        @Provides
        @ApplicationScope
        fun provideRickAndMortyApiDao(
            application: Application
        ): RickAndMortyApiDao {
            return AppDataBase.getInstance(application).rickAndMortyApiDao()
        }

        @Provides
        @ApplicationScope
        fun provideRickAndMortyApiService(): RickAndMortyApiService {
            return ApiFactory.rickAndMortyApiService
        }

    }

}