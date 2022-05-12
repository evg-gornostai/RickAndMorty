package com.gornostai.rickandmorty.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gornostai.rickandmorty.data.local.models.EpisodeDbModel

@Dao
interface EpisodesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addEpisode(episodeDbModel: EpisodeDbModel)

    @Query("SELECT * FROM episodes_table WHERE id =:episodeId LIMIT 1")
    suspend fun getEpisode(episodeId: Int): EpisodeDbModel

    @Query("SELECT * FROM episodes_table")
    suspend fun getEpisodesList(): List<EpisodeDbModel>

}