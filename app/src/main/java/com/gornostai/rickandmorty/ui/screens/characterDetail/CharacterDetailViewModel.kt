package com.gornostai.rickandmorty.ui.screens.characterDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gornostai.rickandmorty.data.repositories.CharactersRepositoryImpl
import com.gornostai.rickandmorty.domain.models.CharacterModel
import com.gornostai.rickandmorty.domain.usecases.GetCharacterItemUseCase

class CharacterDetailViewModel : ViewModel() {

    private val repository = CharactersRepositoryImpl

    private val getCharacterItemUseCase = GetCharacterItemUseCase(repository)

    private val _characterItem = MutableLiveData<CharacterModel>()
    val characterItem: LiveData<CharacterModel>
        get() = _characterItem

    fun getCharacterItem(characterItemId: Int){
        val item = getCharacterItemUseCase.getCharacterItem(characterItemId)
        _characterItem.value = item
    }

}