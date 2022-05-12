package com.gornostai.rickandmorty.presentation.screens.episodes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gornostai.rickandmorty.domain.entities.EpisodeEntity
import com.gornostai.rickandmorty.domain.usecases.GetEpisodesListUseCase
import com.gornostai.rickandmorty.domain.usecases.LoadEpisodesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class EpisodesViewModel @Inject constructor(
    private val getEpisodesListUseCase: GetEpisodesListUseCase,
    private val loadEpisodesUseCase: LoadEpisodesUseCase
) : ViewModel() {

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
            if (episodes.isEmpty()) {
                loadEpisodesUseCase.loadData()
                episodes = getEpisodesListUseCase.getEpisodesList()
            }
            _isLoading.postValue(false)
            _episodesList.postValue(episodes)
        }
    }

    fun refreshData() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            loadEpisodesUseCase.loadData()
            val episodes = getEpisodesListUseCase.getEpisodesList()
            _isLoading.postValue(false)
            _episodesList.postValue(episodes)
        }
    }

}