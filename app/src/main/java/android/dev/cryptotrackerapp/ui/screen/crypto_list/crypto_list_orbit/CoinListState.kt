package android.dev.cryptotrackerapp.ui.screen.crypto_list.crypto_list_orbit

import android.dev.cryptotrackerapp.domain.model.CoinModel
import android.dev.cryptotrackerapp.ui.screen.crypto_list.CryptoListTab
import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList

@Immutable
data class CoinListState(
    val tab: CryptoListTab = CryptoListTab.USD,
    val tabList : ImmutableList<CryptoListTab> = CryptoListTab.entries.toImmutableList(),
    val name: Any = "",
    val list: ImmutableList<CoinModel> = persistentListOf(),
    val state: UIState = UIState.Idle,
)

sealed class UIState {
    object Idle : UIState()
    object Loading : UIState()
    object Error : UIState()
}