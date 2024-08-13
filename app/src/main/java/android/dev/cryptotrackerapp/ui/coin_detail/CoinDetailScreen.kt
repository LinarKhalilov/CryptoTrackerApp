package android.dev.cryptotrackerapp.ui.coin_detail

import android.dev.cryptotrackerapp.R
import android.dev.cryptotrackerapp.ui.coin_detail.detail_orbit.CoinDetailAction
import android.dev.cryptotrackerapp.ui.coin_detail.detail_orbit.CoinDetailsState
import android.dev.cryptotrackerapp.ui.theme.ApplicationTheme
import android.dev.cryptotrackerapp.ui.theme.ApplicationTheme.typography
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun CoinDetailScreen(
    state: CoinDetailsState,
    onAction: (CoinDetailAction) -> Any
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(
                start = 16.dp, end = 16.dp, top = 8.dp
            ),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = state.coinDetails?.imageUrl,
                contentDescription = "Icon",
                modifier = Modifier.size(90.dp)
            )
        }
        HeaderWithText(
            header = stringResource(id = R.string.coin_details_description),
            text = state.coinDetails?.description ?: ""
        )
        HeaderWithText(
            header = stringResource(id = R.string.coin_details_categories),
            text = state.coinDetails?.categories?.joinToString() ?: ""
        )
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
private fun HeaderWithText(
    header : String,
    text : String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = header, style = typography.headers.h6)
        Text(text = text, style = typography.body.body1)
    }
}

@Preview
@Composable
fun CoinDetailScreenPreview() {
    ApplicationTheme {
        CoinDetailScreen(
            state = CoinDetailsState(),
            onAction = {}
        )
    }
}