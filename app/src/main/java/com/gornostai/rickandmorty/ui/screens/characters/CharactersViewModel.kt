package com.gornostai.rickandmorty.ui.screens.characters

import androidx.lifecycle.ViewModel
import com.gornostai.rickandmorty.data.repositories.CharactersRepositoryImpl
import com.gornostai.rickandmorty.domain.usecases.GetCharactersListUseCase

class CharactersViewModel: ViewModel() {

    private val repository = CharactersRepositoryImpl

    private val getCharactersListUseCase = GetCharactersListUseCase(repository)

    val charactersList = getCharactersListUseCase.getCharactersList()


}