package dominicschumerth.artinstituteofchicago.data

import androidx.annotation.WorkerThread
import com.dschumerth.abstractions.Artwork
import com.dschumerth.api.ApiService
import com.dschumerth.api.response.ArtworkData
import com.dschumerth.database.daos.ArtworkDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val apiService: ApiService,
    private val artworkDao: ArtworkDao
) {

    suspend fun getRepoArtwork(): List<Artwork> {
        return artworkDao.getArtworkFromDB()
    }

    @WorkerThread
    suspend fun insertInDatabase(art: Artwork) {
        artworkDao.insertArtInDB(art)
    }

    @WorkerThread
    suspend fun emptyDatabase() {
        artworkDao.deleteAllInDB()
    }

    suspend fun getFromApiArtwork(page: Int): List<ArtworkData> {
        return apiService.getArtworks(page).artworkList
    }
}
