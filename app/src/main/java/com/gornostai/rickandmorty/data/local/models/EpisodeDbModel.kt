package com.gornostai.rickandmorty.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.gornostai.rickandmorty.data.local.converters.StringConverter

@Entity(tableName = "episodes_table")
data class EpisodeDbModel(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val air_date: String,
    @field:TypeConverters(StringConverter::class)
    val characters: List<String>,
    val created: String,
    val episode: String,
    val name: String,
)