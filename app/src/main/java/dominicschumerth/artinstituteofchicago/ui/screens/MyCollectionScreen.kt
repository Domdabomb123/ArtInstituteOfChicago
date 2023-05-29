package dominicschumerth.artinstituteofchicago.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.dschumerth.abstractions.Artwork
import dominicschumerth.artinstituteofchicago.constants.Constants
import dominicschumerth.artinstituteofchicago.ui.MainViewModel

@Composable
fun MyCollectionScreen(
    onArtDetailClicked: (Artwork) -> Unit
) {
    val mainViewModel = hiltViewModel<MainViewModel>()
    Surface(color = MaterialTheme.colorScheme.background) {
        MyCollection(artworks = mainViewModel.myCollection, onArtDetailClicked) {
            mainViewModel.emptyCollection()
        }
        mainViewModel.getMyCollection()
    }
}

@Composable
fun MyCollection(
    artworks: List<Artwork>,
    onArtDetailClicked: (Artwork) -> Unit,
    onEmptyCollectionClicked: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxHeight()
    ) {
        LazyColumn(Modifier.weight(1f)) {
            itemsIndexed(items = artworks) { _, item ->
                CollectionItem(artwork = item) { art ->
                    onArtDetailClicked(art)
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(top = 5.dp)
                    .background(
                        MaterialTheme.colorScheme.background,
                        RoundedCornerShape(10.dp)
                    ),
                onClick = {
                    onEmptyCollectionClicked()
                }) {
                Text(
                    text = "Empty Collection",
                    color = MaterialTheme.colorScheme.background,
                    fontSize = 20.sp,
                    modifier = Modifier.wrapContentSize(Alignment.Center)
                )
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun CollectionItem(
    artwork: Artwork,
    onClick: (Artwork) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp, 4.dp)
            .fillMaxWidth()
            .clickable { onClick(artwork) }
            .height(110.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation()
    ) {
        Surface(color = MaterialTheme.colorScheme.background) {
            Row(
                Modifier
                    .padding(4.dp)
                    .fillMaxSize()
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = Constants.IMAGE_URL_START
                                + artwork.image + Constants.IMAGE_URL_END,
                        builder = {
                            scale(Scale.FILL)
                        }
                    ),
                    contentDescription = artwork.name,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.2f)
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxHeight()
                        .weight(0.8f)
                ) {
                    artwork.name?.let { title ->
                        Text(
                            text = title,
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
    Divider(color = MaterialTheme.colorScheme.secondary, thickness = 1.dp, modifier = Modifier.padding(start = 16.dp, end = 16.dp))
}