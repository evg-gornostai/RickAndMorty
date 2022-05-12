package com.gornostai.rickandmorty.presentation.screens.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gornostai.rickandmorty.domain.entities.CharacterEntity
import com.gornostai.rickandmorty.domain.usecases.GetCharactersListUseCase
import com.gornostai.rickandmorty.domain.usecases.LoadCharactersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharactersViewModel @Inject constructor(
    private val getCharactersListUseCase: GetCharactersListUseCase,
    private val loadCharactersUseCase: LoadCharactersUseCase,
) : ViewModel() {

    private val _charactersList = MutableLiveData<List<CharacterEntity>>()
    val charactersList: LiveData<List<CharacterEntity>>
        get() = _charactersList

    private val _isLoading = MutableLiveData<Boolean>().apply { value = false }
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            var characters = getCharactersListUseCase.getCharactersList()
            if (characters.isEmpty()) {
                loadCharactersUseCase.loadData()
                characters = getCharactersListUseCase.getCharactersList()
            }
            _isLoading.postValue(false)
            _charactersList.postValue(characters)
        }
    }

    fun refreshData() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            loadCharactersUseCase.loadData()
            val characters = getCharactersListUseCase.getCharactersList()
            _isLoading.postValue(false)
            _charactersList.postValue(characters)
        }
    }

}