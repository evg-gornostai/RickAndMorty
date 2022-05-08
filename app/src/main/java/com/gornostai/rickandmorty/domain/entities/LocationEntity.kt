package com.gornostai.rickandmorty.domain.entities

data class LocationEntity(
    val created: String,
    val dimension: String,
    val id: Int,
    val name: String,
    val residents: List<String>,
    val type: String
)