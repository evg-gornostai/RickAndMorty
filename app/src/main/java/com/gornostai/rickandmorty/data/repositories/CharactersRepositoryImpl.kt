package com.gornostai.rickandmorty.data.repositories

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.gornostai.rickandmorty.data.local.AppDataBase
import com.gornostai.rickandmorty.data.local.dao.CharactersDao
import com.gornostai.rickandmorty.data.local.models.CharacterDbModel
import com.gornostai.rickandmorty.data.mappers.CharacterMapper
import com.gornostai.rickandmorty.data.remote.ApiFactory
import com.gornostai.rickandmorty.data.remote.services.CharactersService
import com.gornostai.rickandmorty.domain.entities.CharacterEntity
import com.gornostai.rickandmorty.domain.entities.CharacterFilterEntity
import com.gornostai.rickandmorty.domain.repositories.CharactersRepository
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val application: Application,
    private val charactersDb: CharactersDao,
    private val charactersService: CharactersService
) : CharactersRepository {

    val db = AppDataBase.getInstance(application).characterDao()
    val service = ApiFactory.characterService

    suspend fun loadData() {
        if (isOnline(application)) {
            var response = service.getCharactersList()
            for (i in response.results) {
                db.addCharacter(CharacterMapper().mapDtoToDbModel(i))
            }
            for (i in 2..response.info.pages) {
                response = service.getCharactersList(i)
                for (j in response.results) {
                    db.addCharacter(CharacterMapper().mapDtoToDbModel(j))
                }
            }
        }
    }

    override suspend fun getCharactersList(): List<CharacterEntity> {
        var data = db.getCharactersList()
        if (data.isNotEmpty()) {
            return data.map { CharacterMapper().mapDbModelToEntity(it) }
        } else {
            loadData()
            data = db.getCharactersList()
            return data.map { CharacterMapper().mapDbModelToEntity(it) }
        }
    }


    override suspend fun getCharacterItem(characterItemId: Int): CharacterEntity {
        val dbModel = db.getCharacter(characterItemId)
        return CharacterMapper().mapDbModelToEntity(dbModel)
    }

    override suspend fun getFilteredCharactersList(filter: CharacterFilterEntity): List<CharacterEntity> {
        val filteredListResponse = charactersService.getFilteredCharactersList(
            name = filter.name,
            status = filter.status,
            species = filter.species,
            type = filter.type,
            gender = filter.gender
        )
        var temp: List<CharacterDbModel>? = null

        if (filteredListResponse.isSuccessful){
            temp = filteredListResponse.body()?.results?.map { CharacterMapper().mapDtoToDbModel(it) }
        }

        return temp?.map { CharacterMapper().mapDbModelToEntity(it) } ?: listOf()
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