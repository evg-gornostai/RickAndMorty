package com.gornostai.rickandmorty.ui.screens.characters.adapters

data class CharacterItemModel(
    val id: Int,
    val name: String,
    val species: String,
    val status: String,
    val gender: String,
    val image: String
)