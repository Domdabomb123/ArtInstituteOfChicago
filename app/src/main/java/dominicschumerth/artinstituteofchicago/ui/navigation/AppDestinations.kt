package dominicschumerth.artinstituteofchicago.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink

interface AppDestination{
    val route: String
    val screenTitle: String
}

object ArtList : AppDestination {
    override val route = "artList"
    override val screenTitle = "Art Institute Of Chicago"
}

object MyCollection: AppDestination {
    override val route = "myCollection"
    override val screenTitle = "My Collection"
}

object ArtDetail: AppDestination {
    override val route = "artDetail"
    override val screenTitle = "Large View"

    const val imageIdArg = "image_id"
    const val artTitleArg = "title"
    const val collectionArg = "collection"
    val routeWithArgs = "$route/{$imageIdArg}/{$artTitleArg}/{$collectionArg}"
    val arguments = listOf(
        navArgument(imageIdArg) { type = NavType.StringType },
        navArgument(artTitleArg) { type = NavType.StringType },
        navArgument(collectionArg) {type = NavType.BoolType}
    )
    val deepLinks = listOf(
        navDeepLink { uriPattern = "artinstituteofchicago://$route/{$imageIdArg}/{$artTitleArg}/{$collectionArg}" }
    )
}

val appScreens = listOf(ArtList, MyCollection, ArtDetail)