package com.gornostai.rickandmorty.domain.models

data class EpisodeModel(
    val air_date: String = "",
    val characters: List<String> = listOf(),
    val episode: String = "",
    val id: Int = -1,
    val name: String = ""
)