package android.dev.cryptotrackerapp.ui.screen.crypto_list

import android.dev.cryptotrackerapp.R
import android.dev.cryptotrackerapp.ui.screen.crypto_list.crypto_list_orbit.CoinListAction
import android.dev.cryptotrackerapp.ui.screen.crypto_list.crypto_list_orbit.CoinListSideEffect
import android.dev.cryptotrackerapp.ui.screen.crypto_list.crypto_list_orbit.UIState
import android.dev.cryptotrackerapp.ui.theme.ApplicationTheme.typography
import android.dev.cryptotrackerapp.ui.theme.components.handle.ErrorScreen
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CryptoListRoute(
    navigateToDetail: (String) -> Unit,
    viewModel: CryptoListViewModel = hiltViewModel()
) {

    val state by viewModel.container.stateFlow.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(viewModel) {
        launch {
            viewModel.container.sideEffectFlow.collect { effect ->
                when (effect) {
                    is CoinListSideEffect.NavigateToDetail -> navigateToDetail(effect.cryptoId)
                    is CoinListSideEffect.ShowErrorSnack -> snackBarHostState.showSnackbar(effect.message)
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.crypto_list_title),
                        style = typography.headers.h6,
                        textAlign = TextAlign.Left,
                    )
                })
        },

        content = { padding ->
            when (state.state) {
                UIState.Idle -> {
                    CryptoListScreen(
                        state = state,
                        onAction = viewModel::action,
                        modifier = Modifier.padding(padding)
                    )
                }

                UIState.Error -> ErrorScreen(
                    modifier = Modifier.padding(padding),
                    onRetry = remember { { viewModel.action(CoinListAction.OnRefresh) } }
                )

                UIState.Loading -> Unit // TODO#3 Добавить LoadingScreen(Modifier.padding(padding)
            }

        }
    )
}

//TODO#4 Сделать превью