package com.gornostai.rickandmorty.data.remote.models.episode

data class EpisodesListDto(
    val info: Info,
    val results: List<EpisodeDto>
)