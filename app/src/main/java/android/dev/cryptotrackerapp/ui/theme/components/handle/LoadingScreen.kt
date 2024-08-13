package android.dev.cryptotrackerapp.ui.theme.components.handle

import android.dev.cryptotrackerapp.ui.theme.ApplicationTheme
import android.dev.cryptotrackerapp.ui.theme.ApplicationTheme.colors
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

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
        CircularProgressIndicator(color = colors.primaryColor)
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    ApplicationTheme {
        LoadingScreen()
    }
}
