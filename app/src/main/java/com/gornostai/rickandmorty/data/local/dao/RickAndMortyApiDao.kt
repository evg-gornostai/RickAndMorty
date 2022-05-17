package com.gornostai.rickandmorty.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gornostai.rickandmorty.data.local.models.CharacterDbModel
import com.gornostai.rickandmorty.data.local.models.EpisodeDbModel
import com.gornostai.rickandmorty.data.local.models.LocationDbModel
import com.gornostai.rickandmorty.domain.entities.CharacterFilterEntity

@Dao
interface RickAndMortyApiDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCharacter(characterDbModel: CharacterDbModel)

    @Query("SELECT * FROM characters_table WHERE id =:characterId LIMIT 1")
    suspend fun getCharacter(characterId: String): CharacterDbModel?

    @Query(
        """SELECT * FROM characters_table 
            WHERE name LIKE :name 
            AND status LIKE :status 
            AND gender LIKE :gender 
            AND type LIKE :type 
            AND species LIKE :species"""
    )
    suspend fun getCharactersList(
        name: String,
        status: String,
        gender: String,
        type: String,
        species: String
    ): List<CharacterDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addEpisode(episodeDbModel: EpisodeDbModel)

    @Query("SELECT * FROM episodes_table WHERE id =:episodeId LIMIT 1")
    suspend fun getEpisode(episodeId: Int): EpisodeDbModel?

    @Query(
        """SELECT * FROM episodes_table 
            WHERE name LIKE :name 
            AND episode LIKE :code"""
    )
    suspend fun getEpisodesList(
        name: String,
        code: String
    ): List<EpisodeDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLocation(locationDbModel: LocationDbModel)

    @Query("SELECT * FROM locations_table WHERE id =:locationId LIMIT 1")
    suspend fun getLocation(locationId: Int): LocationDbModel?

    @Query(
        """SELECT * FROM locations_table 
            WHERE name LIKE :name 
            AND type LIKE :type 
            AND dimension LIKE :dimension"""
    )
    suspend fun getLocationsList(
        name: String,
        type: String,
        dimension: String
    ): List<LocationDbModel>

}