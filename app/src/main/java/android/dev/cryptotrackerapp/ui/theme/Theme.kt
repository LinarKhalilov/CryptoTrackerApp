package android.dev.cryptotrackerapp.ui.theme

import android.dev.cryptotrackerapp.ui.theme.palette.Colors
import android.dev.cryptotrackerapp.ui.theme.palette.lightPalette
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

private val LightColorScheme = lightColorScheme(
)

@Composable
fun ApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = when (darkTheme) {
        true -> lightPalette
        else -> lightPalette
    }

    val typography = Typography(
        headers = Headers.getDefault(),
        body = Body.getDefault()
    )


    CompositionLocalProvider(
        LocalApplicationTypography provides typography,
        LocalApplicationColors provides colors,
        content = content
    )
}

object ApplicationTheme {

    val colors: Colors
        @Composable
        get() = LocalApplicationColors.current

    val typography: Typography
        @Composable
        get() = LocalApplicationTypography.current

}

val LocalApplicationColors = staticCompositionLocalOf<Colors> { error("No colors provides") }
val LocalApplicationTypography =
    staticCompositionLocalOf<Typography> { error("No colors provides") }