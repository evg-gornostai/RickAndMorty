package com.gornostai.rickandmorty.presentation.screens.characterDetails

import android.app.Application
import androidx.lifecycle.*
import com.gornostai.rickandmorty.data.repositories.CharactersRepositoryImpl
import com.gornostai.rickandmorty.domain.entities.CharacterEntity
import com.gornostai.rickandmorty.domain.usecases.GetCharacterItemUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterDetailsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CharactersRepositoryImpl(application)

    private val getCharacterItemUseCase = GetCharacterItemUseCase(repository)

    private val _characterItem = MutableLiveData<CharacterEntity>()
    val characterItem: LiveData<CharacterEntity>
        get() = _characterItem

    private val _isLoading = MutableLiveData<Boolean>().apply { value = false }
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun getCharacterItem(characterItemId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            val item = getCharacterItemUseCase.getCharacterItem(characterItemId)
            _isLoading.postValue(false)
            _characterItem.postValue(item)
        }
    }

}