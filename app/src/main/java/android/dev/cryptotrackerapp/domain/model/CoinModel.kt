package android.dev.cryptotrackerapp.domain.model

import com.google.gson.annotations.SerializedName

data class CoinModel(
    val id: String,
    val symbol: String? = null,
    @SerializedName("current_price")
    val currentPrice: Double? = null,
    val name: String? = null,
    val image: String? = null,
    @SerializedName("price_change_percentage_24h")
    val priceChangePercentage24h: Double? = null
)