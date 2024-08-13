package android.dev.cryptotrackerapp.ui.theme.components

import android.dev.cryptotrackerapp.ui.theme.ApplicationTheme
import android.dev.cryptotrackerapp.ui.theme.ApplicationTheme.colors
import android.dev.cryptotrackerapp.ui.theme.ApplicationTheme.typography
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CryptoButton(
    title: String,
    onClick: () -> Unit,
) {
    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = colors.primaryColor,
            contentColor = Color.White,
        ),
        onClick = onClick,
        shape = RoundedCornerShape(4.dp)
    ) {
        Text(
            text = title.uppercase(),
            style = typography.body.body1
        )
    }
}

@Preview
@Composable
fun CryptoButtonPreview() {
    ApplicationTheme {
        CryptoButton(
            title = "Button",
            onClick = { }
        )
    }
}