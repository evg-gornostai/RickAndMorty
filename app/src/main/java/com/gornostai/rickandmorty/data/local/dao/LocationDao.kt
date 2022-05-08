package com.gornostai.rickandmorty.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gornostai.rickandmorty.data.local.models.LocationDbModel

@Dao
interface LocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLocation(locationDbModel: LocationDbModel)

    @Query("SELECT * FROM locations_table WHERE id =:locationId LIMIT 1")
    suspend fun getLocation(locationId: Int): LocationDbModel

    @Query("SELECT * FROM locations_table")
    suspend fun getLocationsList(): List<LocationDbModel>

}