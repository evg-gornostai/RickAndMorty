package com.gornostai.rickandmorty.data.repositories

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.gornostai.rickandmorty.data.local.AppDataBase
import com.gornostai.rickandmorty.data.local.dao.LocationsDao
import com.gornostai.rickandmorty.data.local.models.LocationDbModel
import com.gornostai.rickandmorty.data.mappers.LocationMapper
import com.gornostai.rickandmorty.data.remote.ApiFactory
import com.gornostai.rickandmorty.data.remote.services.LocationsService
import com.gornostai.rickandmorty.domain.entities.LocationEntity
import com.gornostai.rickandmorty.domain.entities.LocationFilterEntity
import com.gornostai.rickandmorty.domain.repositories.LocationsRepository
import javax.inject.Inject

class LocationsRepositoryImpl @Inject constructor(
    private val application: Application,
    private val locationsDb: LocationsDao,
    private val locationsService: LocationsService
) : LocationsRepository {

    val db = AppDataBase.getInstance(application).locationDao()
    val service = ApiFactory.locationService

    suspend fun loadData() {
        if (isOnline(application)) {
            var response = service.getLocationsList()
            for (i in response.results) {
                db.addLocation(LocationMapper().mapDtoToDbModel(i))
            }
            for (i in 2..response.info.pages) {
                response = service.getLocationsList(i)
                for (j in response.results) {
                    db.addLocation(LocationMapper().mapDtoToDbModel(j))
                }
            }
        }
    }

    override suspend fun getLocationsList(): List<LocationEntity> {
        var data = db.getLocationsList()
        if (data.isNotEmpty()){
            return  data.map { LocationMapper().mapDbModelToEntity(it) }
        } else {
            loadData()
            data = db.getLocationsList()
            return  data.map { LocationMapper().mapDbModelToEntity(it) }
        }
    }

    override suspend fun getLocationItem(locationItemId: Int): LocationEntity {
        val dbModel = db.getLocation(locationItemId)
        return LocationMapper().mapDbModelToEntity(dbModel)
    }

    override suspend fun getFilteredLocationsList(filter: LocationFilterEntity): List<LocationEntity> {
        val filteredListResponse = locationsService.getFilteredLocationsList(
            name = filter.name,
            type = filter.type,
            dimension = filter.dimension
        )
        var temp: List<LocationDbModel>? = null
        if (filteredListResponse.isSuccessful){
            temp = filteredListResponse.body()?.results?.map { LocationMapper().mapDtoToDbModel(it) }
        }
        return temp?.map { LocationMapper().mapDbModelToEntity(it) } ?: listOf()
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