package com.gornostai.rickandmorty.domain.entities

data class CharacterFilterEntity(
    val name: String = "",
    val status: String = "",
    val species: String = "",
    val type: String = "",
    val gender: String = ""
)
