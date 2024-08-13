package android.dev.cryptotrackerapp.ui.coin_detail.detail_orbit

import android.dev.cryptotrackerapp.domain.model.CoinDetail
import android.dev.cryptotrackerapp.ui.screen.crypto_list.crypto_list_orbit.UIState

data class CoinDetailsState(
    val coinDetails: CoinDetail? = null,
    val state : UIState = UIState.Idle
)