package com.gornostai.rickandmorty.data.repositories

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.gornostai.rickandmorty.data.local.AppDataBase
import com.gornostai.rickandmorty.data.local.dao.EpisodesDao
import com.gornostai.rickandmorty.data.mappers.EpisodeMapper
import com.gornostai.rickandmorty.data.remote.ApiFactory
import com.gornostai.rickandmorty.data.remote.services.EpisodesService
import com.gornostai.rickandmorty.domain.entities.EpisodeEntity
import com.gornostai.rickandmorty.domain.repositories.EpisodesRepository
import javax.inject.Inject

class EpisodesRepositoryImpl @Inject constructor(
    private val application: Application,
    private val episodesDb: EpisodesDao,
    private val episodesService: EpisodesService
) : EpisodesRepository {

    val db = AppDataBase.getInstance(application).episodeDao()
    val service = ApiFactory.episodeService

    override suspend fun loadData() {
        if (isOnline(application)) {
            var response = service.getEpisodesList()
            for (i in response.results) {
                db.addEpisode(EpisodeMapper().mapDtoToDbModel(i))
            }
            for (i in 2..response.info.pages) {
                response = service.getEpisodesList(i)
                for (j in response.results) {
                    db.addEpisode(EpisodeMapper().mapDtoToDbModel(j))
                }
            }
        }
    }

    override suspend fun getEpisodesList(): List<EpisodeEntity> {
        return db.getEpisodesList().map {
            EpisodeMapper().mapDbModelToEntity(it)
        }
    }

    override suspend fun getEpisodeItem(episodeItemId: Int): EpisodeEntity {
        val dbModel = db.getEpisode(episodeItemId)
        return EpisodeMapper().mapDbModelToEntity(dbModel)
    }

    private fun isOnline(application: Application): Boolean {
        var result = false
        val connectivityManager =
            application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                    ?: return false
            result = when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo ?: return false
            result = when (networkInfo.type) {
                ConnectivityManager.TYPE_WIFI -> true
                ConnectivityManager.TYPE_MOBILE -> true
                ConnectivityManager.TYPE_ETHERNET -> true
                else -> false
            }
        }
        return result
    }

}