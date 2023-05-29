package dominicschumerth.artinstituteofchicago.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dschumerth.api.ApiService
import com.dschumerth.database.daos.ArtworkDao
import com.google.common.truth.Truth.assertThat
import dominicschumerth.artinstituteofchicago.TestCoroutineRule
import dominicschumerth.artinstituteofchicago.data.Repository
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
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var viewModel: MainViewModel

    @Mock
    lateinit var appRepository: Repository

    @Mock
    lateinit var appApi: ApiService

    @Mock
    lateinit var artworkDao: ArtworkDao

    @Before
    fun setUp() {
        appRepository = Repository(appApi, artworkDao)
        viewModel = MainViewModel(appRepository)
    }

    @Test
    fun addToCollection() {
        runTest {
            val addToCollection = viewModel.saveArtToCollection("img", "title")
            assertThat(addToCollection)
        }
    }

    @Test
    fun emptyCollection() {
        runTest {
            val emptyCollection = viewModel.emptyCollection()
            assertThat(emptyCollection)
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}