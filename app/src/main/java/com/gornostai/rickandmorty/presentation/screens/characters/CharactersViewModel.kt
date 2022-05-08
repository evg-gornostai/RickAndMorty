package com.gornostai.rickandmorty.presentation.screens.characters

import android.app.Application
import androidx.lifecycle.*
import com.gornostai.rickandmorty.data.repositories.CharactersRepositoryImpl
import com.gornostai.rickandmorty.domain.entities.CharacterEntity
import com.gornostai.rickandmorty.domain.usecases.GetCharactersListUseCase
import com.gornostai.rickandmorty.domain.usecases.LoadCharactersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharactersViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CharactersRepositoryImpl(application)

    private val getCharactersListUseCase = GetCharactersListUseCase(repository)
    private val loadCharactersUseCase = LoadCharactersUseCase(repository)

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
            if (characters.isEmpty()){
                loadCharactersUseCase.loadData()
                characters = getCharactersListUseCase.getCharactersList()
            }
            _isLoading.postValue(false)
            _charactersList.postValue(characters)
        }
    }

}