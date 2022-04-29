package com.gornostai.rickandmorty.ui.screens.episodeDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gornostai.rickandmorty.data.repositories.EpisodesRepositoryImpl
import com.gornostai.rickandmorty.domain.models.EpisodeModel
import com.gornostai.rickandmorty.domain.usecases.GetEpisodeItemUseCase

class EpisodeDetailsViewModel : ViewModel() {

    private val repository = EpisodesRepositoryImpl

    private val getEpisodeItemUseCase = GetEpisodeItemUseCase(repository)

    private val _episodeItem = MutableLiveData<EpisodeModel>()
    val episodeItem: LiveData<EpisodeModel>
        get () = _episodeItem

    fun getEpisodeItem(episodeItemId: Int){
        val item = getEpisodeItemUseCase.getEpisodeItem(episodeItemId)
        _episodeItem.value = item
    }

}