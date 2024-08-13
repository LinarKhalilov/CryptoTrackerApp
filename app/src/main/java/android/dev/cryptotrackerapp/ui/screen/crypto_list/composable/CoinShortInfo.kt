package android.dev.cryptotrackerapp.ui.screen.crypto_list.composable

import android.dev.cryptotrackerapp.domain.model.CoinModel
import android.dev.cryptotrackerapp.ui.screen.crypto_list.CryptoListTab
import android.dev.cryptotrackerapp.ui.theme.ApplicationTheme
import android.dev.cryptotrackerapp.ui.theme.ApplicationTheme.colors
import android.dev.cryptotrackerapp.ui.theme.ApplicationTheme.typography
import android.dev.cryptotrackerapp.ui.theme.palette.SignalGreen
import android.dev.cryptotrackerapp.ui.theme.palette.SignalRed
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun CoinShortInfo(
    coin: CoinModel,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit,
    currency: CryptoListTab = CryptoListTab.USD
) {

    Row(
        modifier = modifier.padding(vertical = 8.dp).clickable(onClick = {
            onClick(coin.id)
        }),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AsyncImage(
            model = coin.image,
            contentDescription = "Icon",
            modifier = Modifier.size(40.dp)
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            CoinNameBlock(
                name = coin.name ?: "",
                price = coin.currentPrice.toString(),
                selected = currency
            )
            CoinStatBlock(
                code = coin.symbol ?: "",
                percent = coin.priceChangePercentage24h ?: 0.0,
            )
        }
    }
}

@Composable
private fun CoinNameBlock(
    name: String,
    price: String,
    selected: CryptoListTab
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = name, style = typography.body.body1, textAlign = TextAlign.Left)
        Text(text = "${selected.sign} $price", style = typography.body.body1, textAlign = TextAlign.Right)
    }
}

@Composable
private fun CoinStatBlock(
    code: String,
    percent: Double,
) {
    val color = if (percent >= 0) SignalGreen else SignalRed
    val percentSign = if (percent >= 0) "+" else ""
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = code.uppercase(),
            style = typography.body.body3.copy(colors.typographyColors.hintTextColor),
            textAlign = TextAlign.Left
        )
        Text(
            text = "$percentSign $percent%",
            style = typography.body.body3.copy(color),
            textAlign = TextAlign.Right
        )
    } // todo: обработать цвет
}

@Preview
@Composable
fun PCoinShortInfo() {
    ApplicationTheme {
        CoinShortInfo(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            coin = CoinModel(
                id = "",
                symbol = "BTC",
                name = "Bitcoin",
                image = "",
                priceChangePercentage24h = 24.5
            ),
            onClick = {_ ->}
        )
    }
}