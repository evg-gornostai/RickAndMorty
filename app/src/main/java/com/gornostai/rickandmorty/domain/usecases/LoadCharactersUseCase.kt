package com.gornostai.rickandmorty.domain.usecases

import com.gornostai.rickandmorty.domain.repositories.CharactersRepository

class LoadCharactersUseCase(private val repository: CharactersRepository) {

    suspend fun loadData(){
        repository.loadData()
    }

}