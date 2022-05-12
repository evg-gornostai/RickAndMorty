package com.gornostai.rickandmorty.utils

object UrlToIdParser {

    fun parseUrlToStringId(url: String) = url.substringAfterLast('/')

}