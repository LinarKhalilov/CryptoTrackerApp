package android.dev.cryptotrackerapp.ui.coin_detail

import android.dev.cryptotrackerapp.ui.coin_detail.detail_orbit.CoinDetailAction
import android.dev.cryptotrackerapp.ui.screen.crypto_list.crypto_list_orbit.UIState
import android.dev.cryptotrackerapp.ui.theme.ApplicationTheme
import android.dev.cryptotrackerapp.ui.theme.ApplicationTheme.typography
import android.dev.cryptotrackerapp.ui.theme.components.handle.ErrorScreen
import android.dev.cryptotrackerapp.ui.theme.components.handle.LoadingScreen
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinDetailRoute(
    navigateBack: () -> Unit,
    viewModel: CoinDetailsViewModel = hiltViewModel()
) {

    val state by viewModel.container.stateFlow.collectAsState()


    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
            ) {
                TopAppBar(
                    title = {
                        Text(
                            state.coinDetails?.name ?: "",
                            style = typography.headers.h6
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = navigateBack) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back button",
                                tint = Color.Black
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                HorizontalDivider(modifier = Modifier.shadow(2.dp)) // TODO#2: Заменить на elevation
            }
        },

        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                when (state.state) {
                    UIState.Idle -> {
                        CoinDetailScreen(
                            state = state,
                            onAction = viewModel::action
                        )
                    }

                    UIState.Error -> ErrorScreen(onRetry = remember { {
                        viewModel.action(CoinDetailAction.Retry)
                    } }
                    )

                    UIState.Loading -> LoadingScreen(Modifier.padding())
                }
            }
        }
    )

}

@Preview
@Composable
fun CoinDetailRoutePreview() {
    ApplicationTheme {
        CoinDetailRoute(navigateBack = {})
    }
}