package com.gornostai.rickandmorty.domain.repositories

import androidx.lifecycle.LiveData
import com.gornostai.rickandmorty.domain.models.CharacterModel

interface CharactersRepository {

    fun getCharactersList(): LiveData<List<CharacterModel>>

}