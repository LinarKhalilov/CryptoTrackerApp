package android.dev.cryptotrackerapp.ui.theme.components.handle

import android.dev.cryptotrackerapp.ui.theme.ApplicationTheme.colors
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(colors.backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        // TODO#2 Circular Indicator с цветом colors.primaryColor
    }
}

//TODO#4 Сделать превью