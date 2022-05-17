package com.gornostai.rickandmorty.presentation.screens.characterDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gornostai.rickandmorty.domain.entities.CharacterEntity
import com.gornostai.rickandmorty.domain.entities.EpisodeEntity
import com.gornostai.rickandmorty.domain.entities.LocationEntity
import com.gornostai.rickandmorty.domain.usecases.GetCharacterItemUseCase
import com.gornostai.rickandmorty.domain.usecases.GetEpisodesListUseCase
import com.gornostai.rickandmorty.domain.usecases.GetLocationItemUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterDetailsViewModel @Inject constructor(
    private val getCharacterItemUseCase: GetCharacterItemUseCase,
    private val getLocationItemUseCase: GetLocationItemUseCase,
    private val getEpisodesListUseCase: GetEpisodesListUseCase
) : ViewModel() {

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

    private val _showErrorMessage = MutableLiveData<Boolean>().apply { value = false }
    val showErrorMessage: LiveData<Boolean>
        get() = _showErrorMessage

    fun loadData(characterItemId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            val characterItem = getCharacterItemUseCase.getCharacterItem(characterItemId)
            val episodesList = mutableListOf<EpisodeEntity>()
            var originLocation: LocationEntity? = null
            var lastLocation: LocationEntity? = null
            if (characterItem != null) {
                if (characterItem.originId.isNotEmpty()) {
                    originLocation =
                        getLocationItemUseCase.getLocationItem(characterItem.originId.toInt())
                }
                if (characterItem.locationId.isNotEmpty()) {
                    lastLocation =
                        getLocationItemUseCase.getLocationItem(characterItem.locationId.toInt())
                }
                if (characterItem.episode.isNotEmpty()){
                    val arrayOfIds = characterItem.episode
                    episodesList.addAll(getEpisodesListUseCase.getEpisodesListByIds(arrayOfIds))
                }
            }
            _isLoading.postValue(false)
            if (characterItem != null) {
                _episodesList.postValue(episodesList.toList())
                _characterItem.postValue(characterItem!!)
            } else {
                _showErrorMessage.postValue(true)
                _showErrorMessage.postValue(false)
            }
            if (originLocation != null) {
                _originLocation.postValue(originLocation!!)
            }
            if (lastLocation != null) {
                _lastLocation.postValue(lastLocation!!)
            }
        }

    }
}