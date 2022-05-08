package com.gornostai.rickandmorty.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.gornostai.rickandmorty.data.local.converters.StringConverter

@Entity(tableName = "characters_table")
data class CharacterDbModel(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val created: String,
    @field:TypeConverters(StringConverter::class)
    val episode: List<String>,
    val gender: String,
    val image: String,
    @ColumnInfo(name = "location_name")
    val locationName: String,
    @ColumnInfo(name = "location_id")
    val locationId: String,
    val name: String,
    @ColumnInfo(name = "origin_name")
    val originName: String,
    @ColumnInfo(name = "origin_id")
    val originId: String,
    val species: String,
    val status: String,
    val type: String,
)