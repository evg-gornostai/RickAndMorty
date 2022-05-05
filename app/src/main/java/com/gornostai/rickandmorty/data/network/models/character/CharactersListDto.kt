package com.gornostai.rickandmorty.data.network.models.character

data class CharactersListDto(
    val info: Info,
    val results: List<CharacterDto>
)