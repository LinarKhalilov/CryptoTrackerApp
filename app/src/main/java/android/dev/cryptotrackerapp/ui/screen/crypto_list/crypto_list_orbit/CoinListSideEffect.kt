package android.dev.cryptotrackerapp.ui.screen.crypto_list.crypto_list_orbit

sealed class CoinListSideEffect {
    class ShowErrorSnack(val message : String) : CoinListSideEffect()
    class NavigateToDetail(val cryptoId : String) : CoinListSideEffect()
}