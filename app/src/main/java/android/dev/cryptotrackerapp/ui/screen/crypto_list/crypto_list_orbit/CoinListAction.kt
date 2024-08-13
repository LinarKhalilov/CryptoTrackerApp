package android.dev.cryptotrackerapp.ui.screen.crypto_list.crypto_list_orbit

import android.dev.cryptotrackerapp.ui.screen.crypto_list.CryptoListTab

sealed interface CoinListAction {
    class UpdateTab(val tab: CryptoListTab) : CoinListAction
    object OnRefresh : CoinListAction
    class OnCoinClick(val id: String) : CoinListAction
}