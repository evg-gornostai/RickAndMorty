package com.gornostai.rickandmorty.domain.models

data class LocationModel(
    val dimension: String,
    val id: Int,
    val name: String,
    val residents: List<String>,
    val type: String
)