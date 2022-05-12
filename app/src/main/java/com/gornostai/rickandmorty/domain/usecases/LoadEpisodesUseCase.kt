package com.gornostai.rickandmorty.domain.usecases

import com.gornostai.rickandmorty.domain.repositories.EpisodesRepository
import javax.inject.Inject

class LoadEpisodesUseCase @Inject constructor(
    private val repository: EpisodesRepository
) {

    suspend fun loadData() {
        repository.loadData()
    }

}