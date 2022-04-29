package com.gornostai.rickandmorty.ui.screens.episodes

import androidx.lifecycle.ViewModel
import com.gornostai.rickandmorty.data.repositories.EpisodesRepositoryImpl
import com.gornostai.rickandmorty.domain.usecases.GetEpisodesListUseCase

class EpisodesViewModel: ViewModel() {

    private val repository = EpisodesRepositoryImpl

    private val getEpisodesListUseCase = GetEpisodesListUseCase(repository)

    val episodesList = getEpisodesListUseCase.getEpisodesList()

}