package com.dschumerth.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dschumerth.database.daos.ArtworkDao

@Database(
    entities = [com.dschumerth.abstractions.Artwork::class],
    version = 1,
    exportSchema = false
)
abstract class AppRoomDatabase : RoomDatabase() {
    abstract fun appDao(): ArtworkDao

    companion object {
        @Volatile
        private var INSTANCE: AppRoomDatabase? = null
        fun getDataBase(context: Context): AppRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppRoomDatabase::class.java,
                    "art_institute_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
