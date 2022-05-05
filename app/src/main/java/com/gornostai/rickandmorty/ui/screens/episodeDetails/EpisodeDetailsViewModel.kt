package com.gornostai.rickandmorty.ui.screens.episodeDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gornostai.rickandmorty.data.repositories.EpisodesRepositoryImpl
import com.gornostai.rickandmorty.domain.models.EpisodeModel
import com.gornostai.rickandmorty.domain.usecases.GetEpisodeItemUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EpisodeDetailsViewModel : ViewModel() {

    private val repository = EpisodesRepositoryImpl()

    private val getEpisodeItemUseCase = GetEpisodeItemUseCase(repository)

    private val _episodeItem = MutableLiveData<EpisodeModel>()
    val episodeItem: LiveData<EpisodeModel>
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