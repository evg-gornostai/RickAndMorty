package com.gornostai.rickandmorty.domain.models

data class LocationModel(
    val dimension: String = "",
    val id: Int = -1,
    val name: String = "",
    val residents: List<String> = listOf(),
    val type: String = ""
)