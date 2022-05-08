package com.gornostai.rickandmorty.presentation.screens.episodeDetails

import android.app.Application
import androidx.lifecycle.*
import com.gornostai.rickandmorty.data.repositories.EpisodesRepositoryImpl
import com.gornostai.rickandmorty.domain.entities.EpisodeEntity
import com.gornostai.rickandmorty.domain.usecases.GetEpisodeItemUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EpisodeDetailsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = EpisodesRepositoryImpl(application)

    private val getEpisodeItemUseCase = GetEpisodeItemUseCase(repository)

    private val _episodeItem = MutableLiveData<EpisodeEntity>()
    val episodeItem: LiveData<EpisodeEntity>
        get() = _episodeItem

    private val _isLoading = MutableLiveData<Boolean>().apply { value = false }
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun getEpisodeItem(episodeItemId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            val item = getEpisodeItemUseCase.getEpisodeItem(episodeItemId)
            _isLoading.postValue(false)
            _episodeItem.postValue(item)
        }
    }

}