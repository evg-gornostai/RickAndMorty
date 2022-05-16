package com.gornostai.rickandmorty.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gornostai.rickandmorty.data.local.models.CharacterDbModel
import com.gornostai.rickandmorty.data.local.models.EpisodeDbModel
import com.gornostai.rickandmorty.data.local.models.LocationDbModel

@Dao
interface RickAndMortyApiDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCharacter(characterDbModel: CharacterDbModel)

    @Query("SELECT * FROM characters_table WHERE id =:characterId LIMIT 1")
    suspend fun getCharacter(characterId: Int): CharacterDbModel?

    @Query("SELECT * FROM characters_table")
    suspend fun getCharactersList(): List<CharacterDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addEpisode(episodeDbModel: EpisodeDbModel)

    @Query("SELECT * FROM episodes_table WHERE id =:episodeId LIMIT 1")
    suspend fun getEpisode(episodeId: Int): EpisodeDbModel?

    @Query("SELECT * FROM episodes_table")
    suspend fun getEpisodesList(): List<EpisodeDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLocation(locationDbModel: LocationDbModel)

    @Query("SELECT * FROM locations_table WHERE id =:locationId LIMIT 1")
    suspend fun getLocation(locationId: Int): LocationDbModel?

    @Query("SELECT * FROM locations_table")
    suspend fun getLocationsList(): List<LocationDbModel>

}