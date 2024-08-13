package android.dev.cryptotrackerapp.navigation

import android.dev.cryptotrackerapp.ui.coin_detail.CoinDetailRoute
import android.dev.cryptotrackerapp.ui.coin_detail.CoinDetailsViewModel
import android.dev.cryptotrackerapp.ui.screen.crypto_list.CryptoListRoute
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationRoot() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.CoinsScreen::class.java.name) {
        composable(Screens.CoinsScreen::class.java.name) {
            println(Screens.CoinsScreen::class.java.name)
            CryptoListRoute(
                navigateToDetail = remember {{ cryptoId ->
                    navController.navigate("S${Screens.CoinDetailScreen::class.java.name}/$cryptoId")
                }}
            )
        }

        composable("S${Screens.CoinDetailScreen::class.java.name}/{cryptoId}") { backStackEntry ->
            CoinDetailRoute(
                navigateBack = remember { { navController.popBackStack() } },
                viewModel = hiltViewModel<CoinDetailsViewModel, CoinDetailsViewModel.CoinDetailsViewModelFactory> {
                    it.create(backStackEntry.arguments?.getString("cryptoId") ?: "")
                }
            )
        }
    }
}