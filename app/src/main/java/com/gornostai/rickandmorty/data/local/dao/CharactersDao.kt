package com.gornostai.rickandmorty.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gornostai.rickandmorty.data.local.models.CharacterDbModel

@Dao
interface CharactersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCharacter(characterDbModel: CharacterDbModel)

    @Query("SELECT * FROM characters_table WHERE id =:characterId LIMIT 1")
    suspend fun getCharacter(characterId: Int): CharacterDbModel

    @Query("SELECT * FROM characters_table")
    suspend fun getCharactersList(): List<CharacterDbModel>

}