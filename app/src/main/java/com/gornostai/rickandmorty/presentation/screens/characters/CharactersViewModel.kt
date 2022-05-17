package com.gornostai.rickandmorty.presentation.screens.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gornostai.rickandmorty.domain.entities.CharacterEntity
import com.gornostai.rickandmorty.domain.entities.CharacterFilterEntity
import com.gornostai.rickandmorty.domain.usecases.GetCharactersListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharactersViewModel @Inject constructor(
    private val getCharactersListUseCase: GetCharactersListUseCase
) : ViewModel() {

    var filter = CharacterFilterEntity()

    private val _charactersList = MutableLiveData<List<CharacterEntity>>()
    val charactersList: LiveData<List<CharacterEntity>>
        get() = _charactersList

    private val _isLoading = MutableLiveData<Boolean>().apply { value = false }
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun fetchData(filter: CharacterFilterEntity = CharacterFilterEntity()) {
        this.filter = filter
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            _charactersList.postValue(listOf())
            val characters = getCharactersListUseCase.getCharactersList(filter)
            _isLoading.postValue(false)
            _charactersList.postValue(characters)
        }
    }

}