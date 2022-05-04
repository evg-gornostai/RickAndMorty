package com.gornostai.rickandmorty.ui.screens.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gornostai.rickandmorty.data.repositories.CharactersRepositoryImpl
import com.gornostai.rickandmorty.domain.models.CharacterModel
import com.gornostai.rickandmorty.domain.usecases.GetCharactersListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharactersViewModel : ViewModel() {

    private val repository = CharactersRepositoryImpl

    private val getCharactersListUseCase = GetCharactersListUseCase(repository)

    private val _charactersList = MutableLiveData<List<CharacterModel>>()
    val charactersList: LiveData<List<CharacterModel>>
        get() = _charactersList

    private val _isLoading = MutableLiveData<Boolean>().apply { value = false }
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            val characters = getCharactersListUseCase.getCharactersList()
            _isLoading.postValue(false)
            _charactersList.postValue(characters)
        }
    }

}