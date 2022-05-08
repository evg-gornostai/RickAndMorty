package com.gornostai.rickandmorty.utills

object UrlToIdParser {

    fun parseUrlToStringId(url: String) = url.substringAfterLast('/')

}