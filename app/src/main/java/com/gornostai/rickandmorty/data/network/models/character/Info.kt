package com.gornostai.rickandmorty.data.network.models.character

data class Info(
    val count: Int,
    val next: String,
    val pages: Int,
    val prev: Any
)