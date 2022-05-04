package com.gornostai.rickandmorty.ui.screens.characterDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gornostai.rickandmorty.data.repositories.CharactersRepositoryImpl
import com.gornostai.rickandmorty.domain.models.CharacterModel
import com.gornostai.rickandmorty.domain.usecases.GetCharacterItemUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterDetailsViewModel : ViewModel() {

    private val repository = CharactersRepositoryImpl

    private val getCharacterItemUseCase = GetCharacterItemUseCase(repository)

    private val _characterItem = MutableLiveData<CharacterModel>()
    val characterItem: LiveData<CharacterModel>
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