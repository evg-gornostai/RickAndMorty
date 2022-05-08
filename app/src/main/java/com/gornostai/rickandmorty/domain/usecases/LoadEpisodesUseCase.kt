package com.gornostai.rickandmorty.domain.usecases

import com.gornostai.rickandmorty.domain.repositories.EpisodesRepository

class LoadEpisodesUseCase(private val repository: EpisodesRepository) {

    suspend fun loadData() {
        repository.loadData()
    }

}