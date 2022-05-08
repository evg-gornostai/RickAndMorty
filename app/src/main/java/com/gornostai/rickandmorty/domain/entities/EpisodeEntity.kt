package com.gornostai.rickandmorty.domain.entities

data class EpisodeEntity(
    val air_date: String,
    val characters: List<String>,
    val created: String,
    val episode: String,
    val id: Int,
    val name: String
)