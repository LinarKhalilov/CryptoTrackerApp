package android.dev.cryptotrackerapp.data.model

import android.dev.cryptotrackerapp.domain.model.CoinDetail
import android.dev.cryptotrackerapp.utils.DataMapper
import com.google.gson.annotations.SerializedName
import kotlinx.collections.immutable.toImmutableList

data class CoinDetailsDTO(
    @SerializedName("id") var id: String? = "",
    @SerializedName("name") var name: String? = "",
    @SerializedName("web_slug") var webSlug: String? = "",
    @SerializedName("categories") var categories: ArrayList<String> = arrayListOf(),
    @SerializedName("description") var description: Description? = Description(),
    @SerializedName("image") var image: Image? = Image()
) : DataMapper<CoinDetailsDTO, CoinDetail> {
    override fun CoinDetailsDTO.mapToDomain() = CoinDetail(
        id = id,
        name = name,
        description = description?.cleanedDescription,
        imageUrl = image?.small,
        categories = categories.toImmutableList(),
        webSlug = webSlug
    )
}

data class Description(
    @SerializedName("en") var en: String? = null,
) {
    // Для редактирования строки при запросе к ней
    val cleanedDescription: String?
        get() = en?.replace("Description(en=", "")?.replace(")", "")
}

data class Image(
    @SerializedName("thumb") var thumb: String? = null,
    @SerializedName("small") var small: String? = null,
    @SerializedName("large") var large: String? = null
)