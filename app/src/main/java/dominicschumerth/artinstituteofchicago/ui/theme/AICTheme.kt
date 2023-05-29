import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import dominicschumerth.artinstituteofchicago.ui.theme.ColorPalette

@Composable
fun AICTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = ColorPalette,
        content = content
    )
}
