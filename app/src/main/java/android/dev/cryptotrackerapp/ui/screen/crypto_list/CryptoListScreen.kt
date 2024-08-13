package android.dev.cryptotrackerapp.ui.screen.crypto_list

import android.dev.cryptotrackerapp.ui.screen.crypto_list.composable.CoinShortInfo
import android.dev.cryptotrackerapp.ui.screen.crypto_list.crypto_list_orbit.CoinListAction
import android.dev.cryptotrackerapp.ui.screen.crypto_list.crypto_list_orbit.CoinListState
import android.dev.cryptotrackerapp.ui.theme.components.AppChip
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CryptoListScreen(
    state: CoinListState,
    onAction: (CoinListAction) -> Unit,
    modifier: Modifier = Modifier
) {

    val containerState = rememberPullToRefreshState()

    LaunchedEffect(containerState.isRefreshing) {
        if (containerState.isRefreshing) {
            onAction(CoinListAction.OnRefresh)
            delay(1000)
            containerState.endRefresh()
        }
    }

    Column(modifier) {
        AppChip(
            selectedItem = state.tab,
            items = state.tabList,
            onItemClick = remember { { tab -> onAction(CoinListAction.UpdateTab(tab)) } },
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
        )
        HorizontalDivider(thickness = 2.dp, color = Color.LightGray)
        Box(
            modifier = Modifier
                .nestedScroll(containerState.nestedScrollConnection)
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 8.dp)
        ) {
            LazyColumn() {
                items(
                    count = state.list.size,
                    key = { index -> state.list[index].id }
                ) { index ->
                    CoinShortInfo(
                        onClick = remember { { id -> onAction(CoinListAction.OnCoinClick(id)) } },
                        coin = state.list[index],
                        currency = state.tab
                    )
                }
            }

            PullToRefreshContainer(
                state = containerState,
                containerColor = Color.Transparent,
                modifier = Modifier.align(Alignment.TopCenter)
            ) // TODO: Добавить индикатор загрузки
        }

    }

}

//TODO#4 Сделать превью