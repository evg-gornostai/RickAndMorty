package com.gornostai.rickandmorty.domain.models

data class EpisodeModel(
    val air_date: String,
    val characters: List<String>,
    val episode: String,
    val id: Int,
    val name: String
)