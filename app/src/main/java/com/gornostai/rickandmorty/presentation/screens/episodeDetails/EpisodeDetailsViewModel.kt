package com.gornostai.rickandmorty.presentation.screens.episodeDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gornostai.rickandmorty.domain.entities.CharacterEntity
import com.gornostai.rickandmorty.domain.entities.EpisodeEntity
import com.gornostai.rickandmorty.domain.usecases.GetCharactersListUseCase
import com.gornostai.rickandmorty.domain.usecases.GetEpisodeItemUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class EpisodeDetailsViewModel @Inject constructor(
    private val getEpisodeItemUseCase: GetEpisodeItemUseCase,
    private val getCharactersListUseCase: GetCharactersListUseCase
) : ViewModel() {

    private val _episodeItem = MutableLiveData<EpisodeEntity>()
    val episodeItem: LiveData<EpisodeEntity>
        get() = _episodeItem

    private val _charactersList = MutableLiveData<List<CharacterEntity>>()
    val charactersList: LiveData<List<CharacterEntity>>
        get() = _charactersList

    private val _isLoading = MutableLiveData<Boolean>().apply { value = false }
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _showErrorMessage = MutableLiveData<Boolean>().apply { value = false }
    val showErrorMessage: LiveData<Boolean>
        get() = _showErrorMessage

    fun loadData(episodeItemId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            val episodeItem = getEpisodeItemUseCase.getEpisodeItem(episodeItemId)
            val charactersList = mutableListOf<CharacterEntity>()
            if (episodeItem != null) {
                if (episodeItem.characters.isNotEmpty()) {
                    val arrayOfIds = episodeItem.characters.toString()
                    charactersList.addAll(getCharactersListUseCase.getCharactersListByIds(arrayOfIds))
                }
            }
            _isLoading.postValue(false)
            if (episodeItem != null) {
                _episodeItem.postValue(episodeItem!!)
                _charactersList.postValue(charactersList.toList())
            } else {
                _showErrorMessage.postValue(true)
                _showErrorMessage.postValue(false)
            }
        }
    }

}