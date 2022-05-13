package com.gornostai.rickandmorty.data.remote.models.character

data class CharacterDto(
    val created: String = "",
    val episode: List<String> = listOf(),
    val gender: String = "",
    val id: Int = -1,
    val image: String = "",
    val location: Location = Location(),
    val name: String = "",
    val origin: Origin = Origin(),
    val species: String = "",
    val status: String = "",
    val type: String = "",
    val url: String = ""
)