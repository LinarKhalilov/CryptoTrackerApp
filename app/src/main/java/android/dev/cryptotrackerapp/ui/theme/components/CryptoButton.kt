package android.dev.cryptotrackerapp.ui.theme.components

import android.dev.cryptotrackerapp.ui.theme.ApplicationTheme.colors
import android.dev.cryptotrackerapp.ui.theme.ApplicationTheme.typography
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun CryptoButton(
    title: String,
    onClick: () -> Unit,
) {
    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = colors.primaryColor,
            contentColor = Color.White
        ),
        onClick = onClick
    ) {
        Text(
            text = title.uppercase(),
            style = typography.body.body1
        )
    }
}

//TODO#4 Сделать превью (2 кнопки)