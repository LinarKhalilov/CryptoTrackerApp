package android.dev.cryptotrackerapp.ui.coin_detail

import android.dev.cryptotrackerapp.data.CryptoRepository
import android.dev.cryptotrackerapp.ui.coin_detail.detail_orbit.CoinDetailAction
import android.dev.cryptotrackerapp.ui.coin_detail.detail_orbit.CoinDetailsSideEffect
import android.dev.cryptotrackerapp.ui.coin_detail.detail_orbit.CoinDetailsState
import android.dev.cryptotrackerapp.ui.screen.crypto_list.crypto_list_orbit.UIState
import androidx.lifecycle.ViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

@HiltViewModel(assistedFactory = CoinDetailsViewModel.CoinDetailsViewModelFactory::class)
class CoinDetailsViewModel@AssistedInject constructor(
    @Assisted private val id : String,
    private val repository: CryptoRepository,
) : ViewModel(), ContainerHost<CoinDetailsState, CoinDetailsSideEffect> {

    override val container: Container<CoinDetailsState, CoinDetailsSideEffect> =
        container(CoinDetailsState()) { loadCoinDetails(id) }

    fun action(action: CoinDetailAction) : Any = when (action) {
        is CoinDetailAction.OnBackClick -> navigateBack()
        CoinDetailAction.Retry -> loadCoinDetails(id)
    }

    private fun loadCoinDetails(id: String) {
        intent {
            reduce { state.copy(state = UIState.Loading) }
            repository.getCoinDetails(id)
                .onSuccess { reduce { state.copy(coinDetails = it, state = UIState.Idle) } }
                .onError { reduce { state.copy(state = UIState.Error) } }
        }
    }

    private fun navigateBack() = intent { postSideEffect(CoinDetailsSideEffect.NavigateBack)  }

    @AssistedFactory
    interface CoinDetailsViewModelFactory {
        fun create(id : String) : CoinDetailsViewModel
    }
}