package com.gornostai.rickandmorty.domain.entities

data class CharacterEntity(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val locationName: String,
    val locationId: String,
    val name: String,
    val originName: String,
    val originId: String,
    val species: String,
    val status: String,
    val type: String
)