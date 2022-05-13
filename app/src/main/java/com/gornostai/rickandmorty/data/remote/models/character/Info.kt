package com.gornostai.rickandmorty.data.remote.models.character

data class Info(
    val count: Int = -1,
    val next: String = "",
    val pages: Int = -1,
    val prev: Any = Any()
)