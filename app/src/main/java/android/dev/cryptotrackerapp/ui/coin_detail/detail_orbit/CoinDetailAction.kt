package android.dev.cryptotrackerapp.ui.coin_detail.detail_orbit

sealed interface CoinDetailAction {
    object OnBackClick : CoinDetailAction
    object Retry : CoinDetailAction
}