package dominicschumerth.artinstituteofchicago.ui

import AICTheme
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dominicschumerth.artinstituteofchicago.R
import dominicschumerth.artinstituteofchicago.ui.navigation.AppDestination
import dominicschumerth.artinstituteofchicago.ui.navigation.AppNavHost
import dominicschumerth.artinstituteofchicago.ui.navigation.ArtList
import dominicschumerth.artinstituteofchicago.ui.navigation.appScreens


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AICTheme {
                val navController = rememberAnimatedNavController()
                val currentBackStack by navController.currentBackStackEntryAsState()
                val currentDestination = currentBackStack?.destination
                val currentScreen =
                    appScreens.find { currentDestination?.route?.contains(it.route) ?: false }
                        ?: ArtList

                Scaffold(
                    topBar = {
                        AppBar(
                            currentScreen = currentScreen,
                            onBackClicked = { navController.popBackStack() }
                        )
                    }
                ) { innerPadding ->
                    AppNavHost(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    currentScreen: AppDestination,
    onBackClicked: () -> Unit
) {
    TopAppBar(
        title = { Text(text = currentScreen.screenTitle) },
        navigationIcon = {
            Box(modifier = Modifier.size(56.dp) ) {
                if (currentScreen.route == ArtList.route) {
                    Image(
                        painterResource(id = R.mipmap.ic_launcher_foreground),
                        contentDescription = "logo",
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    IconButton(onClick = onBackClicked,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "back")
                    }
                }
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.secondary
        )
    )
}

@Preview(name = "Top Bar", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun AppBarPreview() {
    AppBar(currentScreen = ArtList, onBackClicked = {})
}

