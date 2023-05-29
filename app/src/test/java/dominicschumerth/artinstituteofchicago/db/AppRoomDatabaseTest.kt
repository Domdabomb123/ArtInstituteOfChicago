package dominicschumerth.artinstituteofchicago.db

import android.content.Context
import androidx.room.Room
import com.dschumerth.abstractions.Artwork
import com.dschumerth.database.AppRoomDatabase
import com.dschumerth.database.daos.ArtworkDao
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AppRoomDatabaseTest {
    @Mock
    private lateinit var appRoomDatabase: AppRoomDatabase

    @Mock
    private lateinit var context: Context

    @Mock
    private lateinit var artworkDao: ArtworkDao

    private val artwork = Artwork(
        "art",
        "img"
    )

    @Test
    fun setUp() {
        appRoomDatabase = Room.inMemoryDatabaseBuilder(context, AppRoomDatabase::class.java).build()
        artworkDao = appRoomDatabase.appDao()
    }

    @After
    fun closeDb() {
        appRoomDatabase.close()
    }

    @Test
    fun getArtworkDao(): Unit = runBlocking {
        artworkDao.insertArtInDB(artwork)
        val getArtwork = artworkDao.getArtworkFromDB()
        assertThat(getArtwork)
    }

    @Test
    fun insertAndDelete(): Unit = runBlocking {
        artworkDao.insertArtInDB(artwork)
        artworkDao.getArtworkFromDB()
        artworkDao.deleteAllInDB()
        val resultsDelete = artworkDao.getArtworkFromDB()
        assertThat(resultsDelete)
    }
}