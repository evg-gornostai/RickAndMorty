package com.gornostai.rickandmorty.data.network.models.episode

data class Info(
    val count: Int,
    val next: String,
    val pages: Int,
    val prev: Any
)