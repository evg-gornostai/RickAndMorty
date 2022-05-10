package com.gornostai.rickandmorty.presentation.screens.episodeDetails

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gornostai.rickandmorty.data.repositories.CharactersRepositoryImpl
import com.gornostai.rickandmorty.data.repositories.EpisodesRepositoryImpl
import com.gornostai.rickandmorty.domain.entities.CharacterEntity
import com.gornostai.rickandmorty.domain.entities.EpisodeEntity
import com.gornostai.rickandmorty.domain.usecases.GetCharacterItemUseCase
import com.gornostai.rickandmorty.domain.usecases.GetEpisodeItemUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EpisodeDetailsViewModel(application: Application) : AndroidViewModel(application) {

    private val episodesRepository = EpisodesRepositoryImpl(application)
    private val charactersRepository = CharactersRepositoryImpl(application)

    private val getEpisodeItemUseCase = GetEpisodeItemUseCase(episodesRepository)
    private val getCharacterItemUseCase = GetCharacterItemUseCase(charactersRepository)

    private val _episodeItem = MutableLiveData<EpisodeEntity>()
    val episodeItem: LiveData<EpisodeEntity>
        get() = _episodeItem

    private val _charactersList = MutableLiveData<List<CharacterEntity>>()
    val charactersList: LiveData<List<CharacterEntity>>
        get() = _charactersList

    private val _isLoading = MutableLiveData<Boolean>().apply { value = false }
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun loadData(episodeItemId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            val episodeItem = getEpisodeItemUseCase.getEpisodeItem(episodeItemId)
            val charactersList = mutableListOf<CharacterEntity>()
            for (i in episodeItem.characters) {
                if (i.isNotEmpty()) {
                    charactersList.add(getCharacterItemUseCase.getCharacterItem(i.toInt()))
                }
            }
            _isLoading.postValue(false)
            _episodeItem.postValue(episodeItem)
            _charactersList.postValue(charactersList.toList())
        }
    }

}