package com.gornostai.rickandmorty.data.remote.models.character

data class CharactersListDto(
    val info: Info = Info(),
    val results: List<CharacterDto> = listOf(),
)