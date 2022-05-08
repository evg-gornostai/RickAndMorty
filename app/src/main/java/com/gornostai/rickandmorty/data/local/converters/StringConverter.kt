package com.gornostai.rickandmorty.data.local.converters

import androidx.room.TypeConverter

class StringConverter {

    @TypeConverter
    fun fromList(list: List<String>): String = list.joinToString(separator = "\n")

    @TypeConverter
    fun toList(str: String): List<String> = str.lines()

}