package android.dev.cryptotrackerapp.data.model

import android.dev.cryptotrackerapp.domain.model.CoinModel
import android.dev.cryptotrackerapp.utils.DataMapper
import android.dev.cryptotrackerapp.utils.mapToDomain
import com.google.gson.annotations.SerializedName

data class CoinModelDTO(
    val id: String,
    val symbol: String? = null,
    @SerializedName("current_price")
    val currentPrice: Double? = null,
    val name: String? = null,
    val image: String? = null,
    @SerializedName("price_change_percentage_24h")
    val priceChangePercentage24h: Double? = null
) : DataMapper<CoinModelDTO, CoinModel> {

    override fun CoinModelDTO.mapToDomain() = CoinModel(
        id = id,
        symbol = symbol,
        currentPrice = currentPrice,
        name = name,
        image = image,
        priceChangePercentage24h = priceChangePercentage24h
    )
}

fun List<CoinModelDTO>.mapToDomain() = map { model -> model.mapToDomain() }
