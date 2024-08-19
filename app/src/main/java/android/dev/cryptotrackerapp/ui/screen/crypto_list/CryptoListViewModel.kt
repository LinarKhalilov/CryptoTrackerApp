package android.dev.cryptotrackerapp.ui.screen.crypto_list

import android.dev.cryptotrackerapp.data.CoinPageSource
import android.dev.cryptotrackerapp.data.CryptoApi
import android.dev.cryptotrackerapp.domain.ICryptoRepository
import android.dev.cryptotrackerapp.domain.model.CoinModel
import android.dev.cryptotrackerapp.ui.screen.crypto_list.crypto_list_orbit.CoinListAction
import android.dev.cryptotrackerapp.ui.screen.crypto_list.crypto_list_orbit.CoinListSideEffect
import android.dev.cryptotrackerapp.ui.screen.crypto_list.crypto_list_orbit.CoinListState
import android.dev.cryptotrackerapp.ui.screen.crypto_list.crypto_list_orbit.UIState
import android.dev.cryptotrackerapp.ui.theme.components.UIModel
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

enum class CryptoListTab(val sign : String) : UIModel {
    USD("$"),
    RUB("₽");

    @Composable
    override fun getString(): String = this.name
}


@HiltViewModel
class CryptoListViewModel @Inject constructor(
    private val cryptoApi: ICryptoRepository,
    private val api: CryptoApi
) : ContainerHost<CoinListState, CoinListSideEffect>, ViewModel() {

    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()
    @OptIn(ExperimentalCoroutinesApi::class)
    val coins: Flow<PagingData<CoinModel>> = query.flatMapLatest { query ->
        Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { CoinPageSource(api, query) }
        ).flow
    }.cachedIn(viewModelScope)

    fun updateQuery(newQuery: String) {
        _query.value = newQuery
    }

    override val container: Container<CoinListState, CoinListSideEffect> =
        container(CoinListState()) { loadCoins("usd") }

    fun action(action: CoinListAction) : Any = when (action) {
        is CoinListAction.UpdateTab -> changeTab(action.tab)
        is CoinListAction.OnRefresh -> loadCoins()
        is CoinListAction.OnCoinClick -> onCoinClick(action.id)
    }

    private fun changeTab(tab: CryptoListTab) {
        intent {
            reduce { state.copy(tab = tab) }
            loadCoins(tab.name)
        }
    }

    private fun loadCoins(currency : String? = null) = intent {
        reduce { state.copy(state = UIState.Loading) }
        val currencyString = currency ?: state.tab.name.lowercase()
        cryptoApi.getList(currencyString)
        cryptoApi.getList(currencyString)
            .onSuccess { reduce { state.copy(list = it.toImmutableList(), state = UIState.Idle) } }
            .onError { reactOnError(it.message) }
    }

    private fun reactOnError(message: String) = intent {
        if(state.list.isEmpty()) {
            reduce { state.copy(state = UIState.Error) }
        } else {
            reduce { state.copy(state = UIState.Idle) }
            postSideEffect(CoinListSideEffect.ShowErrorSnack(message))
        }
    }

    private fun onCoinClick(id: String) {
        intent { postSideEffect(CoinListSideEffect.NavigateToDetail(id)) }
    }
}