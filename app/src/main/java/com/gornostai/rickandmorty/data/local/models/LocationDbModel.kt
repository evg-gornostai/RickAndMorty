package com.gornostai.rickandmorty.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.gornostai.rickandmorty.data.local.converters.StringConverter

@Entity(tableName = "locations_table")
data class LocationDbModel(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val created: String,
    val dimension: String,
    val name: String,
    @field:TypeConverters(StringConverter::class)
    val residents: List<String>,
    val type: String,
)