package dominicschumerth.artinstituteofchicago.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import com.dschumerth.abstractions.Artwork
import dominicschumerth.artinstituteofchicago.data.Repository
import com.dschumerth.api.response.ArtworkData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    var artworksResponse: List<ArtworkData>? by mutableStateOf(listOf())
    var myCollection: List<Artwork> by mutableStateOf(listOf())
    var page: Int by mutableStateOf(1)

    init {
        getCharacterList()
    }
    private fun getCharacterList() {
        artworksResponse = null
        viewModelScope.launch {
            artworksResponse = repository.getFromApiArtwork(page)
        }
    }

    fun onNextClicked() {
        artworksResponse = null
        page++
        viewModelScope.launch {
            artworksResponse = repository.getFromApiArtwork(page)
        }
    }

    fun onPreviousClicked() {
        artworksResponse = null
        page--
        viewModelScope.launch {
            artworksResponse = repository.getFromApiArtwork(page)
        }
    }

    fun saveArtToCollection(imageId: String?, title: String) {
        viewModelScope.launch {
            repository.insertInDatabase(Artwork(title, imageId))
        }
    }

    fun getMyCollection() {
        viewModelScope.launch {
            myCollection = repository.getRepoArtwork()
        }
    }

    fun emptyCollection() {
        viewModelScope.launch {
            repository.emptyDatabase()
            myCollection = repository.getRepoArtwork()
        }
    }
}
