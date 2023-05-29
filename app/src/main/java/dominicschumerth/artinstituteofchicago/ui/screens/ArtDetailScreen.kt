package dominicschumerth.artinstituteofchicago.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.size.Scale
import dominicschumerth.artinstituteofchicago.constants.Constants
import dominicschumerth.artinstituteofchicago.ui.MainViewModel

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ArtDetailScreen(
    imageId: String?,
    title: String,
    collection: Boolean,
    onBackTriggered: () -> Unit
) {
    val mainViewModel = hiltViewModel<MainViewModel>()
    Box(
        Modifier
            .fillMaxSize()
            .padding(top = 5.dp)
            .background(MaterialTheme.colorScheme.background, RoundedCornerShape(10.dp))
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = rememberImagePainter(
                    data = Constants.IMAGE_URL_START
                            + imageId + Constants.IMAGE_URL_END,
                    builder = {
                        scale(Scale.FILL)
                    }
                ),
                contentDescription = title,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.2f)
            )
            Text(
                text = title,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(12.dp),
                fontSize = 24.sp
            )
            if (!collection) {
                Button(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(top = 5.dp)
                        .background(
                            MaterialTheme.colorScheme.background,
                            RoundedCornerShape(10.dp)
                        ),
                    onClick = {
                        mainViewModel.saveArtToCollection(imageId, title)
                        onBackTriggered()
                    }) {
                    Text(
                        text = "Save",
                        color = MaterialTheme.colorScheme.background,
                        fontSize = 20.sp,
                        modifier = Modifier.wrapContentSize(Alignment.Center)
                    )
                }
            }
        }
    }
}