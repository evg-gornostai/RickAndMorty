package com.gornostai.rickandmorty.data.local

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gornostai.rickandmorty.data.local.dao.CharactersDao
import com.gornostai.rickandmorty.data.local.dao.EpisodesDao
import com.gornostai.rickandmorty.data.local.dao.LocationsDao
import com.gornostai.rickandmorty.data.local.models.CharacterDbModel
import com.gornostai.rickandmorty.data.local.models.EpisodeDbModel
import com.gornostai.rickandmorty.data.local.models.LocationDbModel

@Database(
    entities = [CharacterDbModel::class, EpisodeDbModel::class, LocationDbModel::class],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun characterDao(): CharactersDao

    abstract fun episodeDao(): EpisodesDao

    abstract fun locationDao(): LocationsDao

    companion object{

        private var INSTANCE: AppDataBase? = null
        private val LOCK = Any()
        private val DB_NAME = "rick_and_morty_api.db"

        fun getInstance(application: Application): AppDataBase{
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK){
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    application,
                    AppDataBase::class.java,
                    DB_NAME
                )
                    .build()
                INSTANCE = db
                return db
            }
        }

    }

}







