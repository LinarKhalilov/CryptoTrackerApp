package android.dev.cryptotrackerapp.ui.coin_detail.detail_orbit

sealed interface CoinDetailsSideEffect {
    object NavigateBack : CoinDetailsSideEffect
}