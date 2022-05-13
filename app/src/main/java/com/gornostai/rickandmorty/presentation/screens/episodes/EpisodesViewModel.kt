package com.gornostai.rickandmorty.presentation.screens.episodes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gornostai.rickandmorty.domain.entities.EpisodeEntity
import com.gornostai.rickandmorty.domain.entities.EpisodeFilterEntity
import com.gornostai.rickandmorty.domain.usecases.GetEpisodesListUseCase
import com.gornostai.rickandmorty.domain.usecases.GetFilteredEpisodesListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class EpisodesViewModel @Inject constructor(
    private val getEpisodesListUseCase: GetEpisodesListUseCase,
    private val getFilteredEpisodesListUseCase: GetFilteredEpisodesListUseCase
) : ViewModel() {

    var filter = EpisodeFilterEntity()

    private val _episodesList = MutableLiveData<List<EpisodeEntity>>()
    val episodesList: LiveData<List<EpisodeEntity>>
        get() = _episodesList

    private val _isLoading = MutableLiveData<Boolean>().apply { value = false }
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun fetchData() {
        filter = EpisodeFilterEntity()
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            val episodes = getEpisodesListUseCase.getEpisodesList()
            _isLoading.postValue(false)
            _episodesList.postValue(episodes)
        }
    }

    fun getFilteredData(filter: EpisodeFilterEntity) {
        this.filter = filter
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            val filteredEpisodes =
                getFilteredEpisodesListUseCase.getFilteredEpisodesList(filter)
            _isLoading.postValue(false)
            _episodesList.postValue(filteredEpisodes)
        }
    }

}