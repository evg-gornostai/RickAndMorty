package com.gornostai.rickandmorty.utills

object UrlToIntParser {

    fun parseUrlToInt(url: String) = url.substringAfterLast('/')

}