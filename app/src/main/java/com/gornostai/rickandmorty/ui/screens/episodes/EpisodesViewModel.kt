package com.gornostai.rickandmorty.ui.screens.episodes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gornostai.rickandmorty.data.repositories.EpisodesRepositoryImpl
import com.gornostai.rickandmorty.domain.models.EpisodeModel
import com.gornostai.rickandmorty.domain.usecases.GetEpisodesListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EpisodesViewModel : ViewModel() {

    private val repository = EpisodesRepositoryImpl()

    private val getEpisodesListUseCase = GetEpisodesListUseCase(repository)

    private val _episodesList = MutableLiveData<List<EpisodeModel>>()
    val episodesList: LiveData<List<EpisodeModel>>
        get() = _episodesList

    private val _isLoading = MutableLiveData<Boolean>().apply { value = false }
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            val episodes = getEpisodesListUseCase.getEpisodesList()
            _isLoading.postValue(false)
            _episodesList.postValue(episodes)
        }
    }

}