package android.dev.cryptotrackerapp.data

import android.dev.cryptotrackerapp.data.model.CoinDetailsDTO
import android.dev.cryptotrackerapp.data.model.CoinModelDTO
import android.dev.cryptotrackerapp.data.model.mapToDomain
import android.dev.cryptotrackerapp.domain.ICryptoRepository
import android.dev.cryptotrackerapp.domain.model.CoinDetail
import android.dev.cryptotrackerapp.domain.model.CoinModel
import android.dev.cryptotrackerapp.utils.AppResponse
import android.dev.cryptotrackerapp.utils.BaseRepository
import android.dev.cryptotrackerapp.utils.mapToDomain
import javax.inject.Inject

class CryptoRepository@Inject constructor(
    private val api  : CryptoApi
) : ICryptoRepository, BaseRepository() {

    override suspend fun getList(currency : String): AppResponse<List<CoinModel>> {
        return doNetworkRequest(mapper = List<CoinModelDTO>::mapToDomain) { api.getCoinsMarkets(currency) }
    }

    override suspend fun getCoinDetails(id: String): AppResponse<CoinDetail> {
        return doNetworkRequest(mapper = CoinDetailsDTO::mapToDomain) {
            api.getCoinDetails(id)
        }
    }
} // todo: Добавить кеширование данных