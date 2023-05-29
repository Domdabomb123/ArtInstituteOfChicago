package dominicschumerth.artinstituteofchicago.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import dominicschumerth.artinstituteofchicago.ui.screens.ArtDetailScreen
import dominicschumerth.artinstituteofchicago.ui.screens.ArtListScreen
import dominicschumerth.artinstituteofchicago.ui.screens.MyCollectionScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = ArtList.route,
        modifier = modifier
    ) {
        composable(
            route = ArtList.route,
            enterTransition = {
                slideInHorizontally(initialOffsetX = { -600 })
            },
            popEnterTransition = {
                slideInHorizontally(initialOffsetX = { -600 })
            }
        ) {
            ArtListScreen(
                onMyCollectionClicked = {
                    navController.navigateToMyCollection()
                },
                onArtDetailClicked = { art ->
                    navController.navigateToArtDetail(art.image_id, art.title)
                }
            )
        }
        composable(
            route = MyCollection.route,
            enterTransition = {
                slideInHorizontally(initialOffsetX = { 600 })
            },
            popEnterTransition = {
                slideInHorizontally(initialOffsetX = { 600 })
            }
        ) {
            MyCollectionScreen(onArtDetailClicked = { art ->
                art.image?.let { img ->
                    art.name?.let { title ->
                        navController.navigateToArtDetail(
                            img,
                            title,
                            true
                        )
                    }
                }
            })
        }
        composable(
            route = ArtDetail.routeWithArgs,
            arguments = ArtDetail.arguments,
            deepLinks = ArtDetail.deepLinks,
            enterTransition = {
                slideInHorizontally(initialOffsetX = { 600 })
            },
            popEnterTransition = {
                slideInHorizontally(initialOffsetX = { 600 })
            }
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.let { args ->
                args.getString(ArtDetail.artTitleArg)?.let { title ->
                    ArtDetailScreen(
                        imageId = args.getString(ArtDetail.imageIdArg),
                        title = title,
                        collection = args.getBoolean(ArtDetail.collectionArg)
                    ) {
                        navController.navigateUp()
                    }
                }
            }
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        popUpTo(
            this@navigateSingleTopTo.currentBackStackEntry?.id ?: return@navigate
        ) {
            inclusive = true
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }

fun NavHostController.navigateToArtDetail(imageId: String, title: String, collection: Boolean = false) {
    this.navigateSingleTopTo("${ArtDetail.route}/$imageId/$title/$collection")
}

fun NavHostController.navigateToMyCollection() {
    this.navigateSingleTopTo(MyCollection.route)
}