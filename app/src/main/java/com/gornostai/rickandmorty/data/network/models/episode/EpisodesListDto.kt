package com.gornostai.rickandmorty.data.network.models.episode

data class EpisodesListDto(
    val info: Info,
    val results: List<EpisodeDto>
)