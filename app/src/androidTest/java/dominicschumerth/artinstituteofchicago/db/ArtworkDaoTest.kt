package dominicschumerth.artinstituteofchicago.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.dschumerth.abstractions.Artwork
import com.dschumerth.database.AppRoomDatabase
import com.dschumerth.database.daos.ArtworkDao
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.CustomTestApplication
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.withContext
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class ArtworkDaoTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    @Inject
    @Named("art_institute_database")
    lateinit var database: AppRoomDatabase
    private lateinit var dao: ArtworkDao
    private val artwork = Artwork(
        "art",
        "img"
    )

    @Before
    fun setUp() {
        hiltRule.inject()
        dao = database.appDao()
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun insertArtwork() = runBlocking {
        dao.insertArtInDB(artwork)
        val artworks = dao.getArtworkFromDB()
        assertThat(artworks).isNotNull()
    }

    @Test
    fun deleteArtwork() = runBlocking {
        dao.insertArtInDB(artwork)
        dao.deleteAllInDB()
        assertThat(dao.getArtworkFromDB()).isEmpty()
    }

    @After
    fun teardown() {
        database.close()
        Dispatchers.resetMain()
    }

    @InternalCoroutinesApi
    @Test
    fun testInsertAndGetAllArtwork() = runBlocking {
        dao.insertArtInDB(artwork)
        val latch = CountDownLatch(1)
        val job = launch(Dispatchers.IO) {
            val firstItem = dao.getArtworkFromDB().first()
            assertThat(firstItem).isNotNull()
            latch.countDown()
        }
        withContext(Dispatchers.IO) {
            latch.await()
        }
        job.cancel()
    }
}