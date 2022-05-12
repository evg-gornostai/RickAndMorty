package com.gornostai.rickandmorty.domain.usecases

import com.gornostai.rickandmorty.domain.repositories.CharactersRepository
import javax.inject.Inject

class LoadCharactersUseCase @Inject constructor(
    private val repository: CharactersRepository
) {

    suspend fun loadData() {
        repository.loadData()
    }

}