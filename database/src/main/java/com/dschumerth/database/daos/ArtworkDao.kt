package com.dschumerth.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dschumerth.abstractions.Artwork

@Dao
abstract class ArtworkDao {

    @Query("SELECT * FROM artwork ORDER BY id DESC")
    abstract suspend fun getArtworkFromDB(): List<Artwork>

    @Insert
    abstract suspend fun insertArtInDB(artwork: Artwork?)

    @Query("DELETE FROM artwork")
    abstract suspend fun deleteAllInDB()
}
