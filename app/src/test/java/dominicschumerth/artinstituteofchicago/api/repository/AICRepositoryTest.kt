package dominicschumerth.artinstituteofchicago.api.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dschumerth.abstractions.Artwork
import com.dschumerth.api.ApiService
import com.dschumerth.api.response.ArtworkResponse
import com.dschumerth.database.daos.ArtworkDao
import com.google.common.truth.Truth.assertThat
import dominicschumerth.artinstituteofchicago.TestCoroutineRule
import dominicschumerth.artinstituteofchicago.data.Repository
import dominicschumerth.artinstituteofchicago.ui.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.lenient
import org.mockito.junit.MockitoJUnitRunner
import javax.inject.Inject

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class AICRepositoryTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var appRepository: Repository

    @Mock
    lateinit var appApi: ApiService

    @Mock
    lateinit var artworkDao: ArtworkDao

    @Mock
    lateinit var artwork: Artwork

    @Mock
    lateinit var artworkResponse: ArtworkResponse

    @Before
    fun setUp() {
        appRepository = Repository(appApi, artworkDao)
        viewModel = MainViewModel(appRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `artwork getArtworks`() =
        runTest {
            lenient().`when`(appApi.getArtworks(1)).thenReturn(artworkResponse)
        }

    @Test
    fun addArtworkToCollection() {
        runTest {
            val addToCollection = appRepository.insertInDatabase(artwork)
            assertThat(addToCollection)
        }
    }

    @Test
    fun removeArtFromCollection() {
        runTest {
            val removeArtFromCollection =
                appRepository.emptyDatabase()
            assertThat(removeArtFromCollection)
        }
    }
}