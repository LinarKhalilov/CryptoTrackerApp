package android.dev.cryptotrackerapp.domain

import android.dev.cryptotrackerapp.domain.model.CoinDetail
import android.dev.cryptotrackerapp.domain.model.CoinModel
import android.dev.cryptotrackerapp.utils.AppResponse

interface ICryptoRepository {
    suspend fun getList(currency : String) : AppResponse<List<CoinModel>>
    suspend fun getCoinDetails(id : String) : AppResponse<CoinDetail>
}

enum class CryptoError(
    val message : String
) {
    NoInternet("Нет интернета"),
    UnknownError("Неизвестная ошибка")
}