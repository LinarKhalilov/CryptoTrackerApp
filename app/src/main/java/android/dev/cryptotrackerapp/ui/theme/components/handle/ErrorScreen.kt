package android.dev.cryptotrackerapp.ui.theme.components.handle

import android.dev.cryptotrackerapp.R
import android.dev.cryptotrackerapp.ui.theme.ApplicationTheme.colors
import android.dev.cryptotrackerapp.ui.theme.ApplicationTheme.typography
import android.dev.cryptotrackerapp.ui.theme.components.CryptoButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

//TODO#6 скругление кнопки по дизайну, перенос текста как в дизайне
@Composable
fun ErrorScreen(
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(colors.backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_btc),
                contentDescription = "Icon",
                modifier = Modifier.size(120.dp)
            )
            Spacer(modifier = Modifier.height(13.dp))
            Text(
                text = stringResource(id = R.string.some_error),
                style = typography.body.body1,
            )
            Spacer(modifier = Modifier.height(30.dp))
            CryptoButton(
                title = stringResource(id = R.string.try_again),
                onClick = onRetry
            )
        }
    }
}

//TODO#4 Сделать превью