package com.gornostai.rickandmorty.presentation.screens.episodes

import android.app.Application
import androidx.lifecycle.*
import com.gornostai.rickandmorty.data.repositories.EpisodesRepositoryImpl
import com.gornostai.rickandmorty.domain.entities.EpisodeEntity
import com.gornostai.rickandmorty.domain.usecases.GetEpisodesListUseCase
import com.gornostai.rickandmorty.domain.usecases.LoadEpisodesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EpisodesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = EpisodesRepositoryImpl(application)

    private val getEpisodesListUseCase = GetEpisodesListUseCase(repository)
    private val loadEpisodesUseCase = LoadEpisodesUseCase(repository)

    private val _episodesList = MutableLiveData<List<EpisodeEntity>>()
    val episodesList: LiveData<List<EpisodeEntity>>
        get() = _episodesList

    private val _isLoading = MutableLiveData<Boolean>().apply { value = false }
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            var episodes = getEpisodesListUseCase.getEpisodesList()
            if (episodes.isEmpty()){
                loadEpisodesUseCase.loadData()
                episodes = getEpisodesListUseCase.getEpisodesList()
            }
            _isLoading.postValue(false)
            _episodesList.postValue(episodes)
        }
    }

}