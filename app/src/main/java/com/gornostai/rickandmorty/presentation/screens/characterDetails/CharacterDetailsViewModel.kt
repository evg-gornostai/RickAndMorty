package com.gornostai.rickandmorty.presentation.screens.characterDetails

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gornostai.rickandmorty.data.repositories.CharactersRepositoryImpl
import com.gornostai.rickandmorty.data.repositories.EpisodesRepositoryImpl
import com.gornostai.rickandmorty.data.repositories.LocationsRepositoryImpl
import com.gornostai.rickandmorty.domain.entities.CharacterEntity
import com.gornostai.rickandmorty.domain.entities.EpisodeEntity
import com.gornostai.rickandmorty.domain.entities.LocationEntity
import com.gornostai.rickandmorty.domain.usecases.GetCharacterItemUseCase
import com.gornostai.rickandmorty.domain.usecases.GetEpisodeItemUseCase
import com.gornostai.rickandmorty.domain.usecases.GetLocationItemUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterDetailsViewModel(application: Application) : AndroidViewModel(application) {

    private val charactersRepository = CharactersRepositoryImpl(application)
    private val episodesRepository = EpisodesRepositoryImpl(application)
    private val locationRepository = LocationsRepositoryImpl(application)

    private val getCharacterItemUseCase = GetCharacterItemUseCase(charactersRepository)
    private val getEpisodeItemUseCase = GetEpisodeItemUseCase(episodesRepository)
    private val getLocationItemUseCase = GetLocationItemUseCase(locationRepository)

    private val _characterItem = MutableLiveData<CharacterEntity>()
    val characterItem: LiveData<CharacterEntity>
        get() = _characterItem

    private val _originLocation = MutableLiveData<LocationEntity>()
    val originLocation: LiveData<LocationEntity>
        get() = _originLocation

    private val _lastLocation = MutableLiveData<LocationEntity>()
    val lastLocation: LiveData<LocationEntity>
        get() = _lastLocation

    private val _episodesList = MutableLiveData<List<EpisodeEntity>>()
    val episodesList: LiveData<List<EpisodeEntity>>
        get() = _episodesList

    private val _isLoading = MutableLiveData<Boolean>().apply { value = false }
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun loadData(characterItemId: Int){
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            val characterItem = getCharacterItemUseCase.getCharacterItem(characterItemId)
            if (characterItem.originId.isNotEmpty()){
                val originLocation = getLocationItemUseCase.getLocationItem(characterItem.originId.toInt())
                _originLocation.postValue(originLocation)
            }
            if (characterItem.locationId.isNotEmpty()){
                val lastLocation = getLocationItemUseCase.getLocationItem(characterItem.locationId.toInt())
                _lastLocation.postValue(lastLocation)
            }
            val episodesList = mutableListOf<EpisodeEntity>()
            for (i in characterItem.episode){
                episodesList.add(getEpisodeItemUseCase.getEpisodeItem(i.toInt()))
            }
            _isLoading.postValue(false)
            _characterItem.postValue(characterItem)
            _episodesList.postValue(episodesList.toList())
        }
    }

}