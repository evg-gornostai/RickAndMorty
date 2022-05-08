package com.gornostai.rickandmorty.data.local

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gornostai.rickandmorty.data.local.dao.CharacterDao
import com.gornostai.rickandmorty.data.local.dao.EpisodeDao
import com.gornostai.rickandmorty.data.local.dao.LocationDao
import com.gornostai.rickandmorty.data.local.models.CharacterDbModel
import com.gornostai.rickandmorty.data.local.models.EpisodeDbModel
import com.gornostai.rickandmorty.data.local.models.LocationDbModel

@Database(
    entities = [CharacterDbModel::class, EpisodeDbModel::class, LocationDbModel::class],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao

    abstract fun episodeDao(): EpisodeDao

    abstract fun locationDao(): LocationDao

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







