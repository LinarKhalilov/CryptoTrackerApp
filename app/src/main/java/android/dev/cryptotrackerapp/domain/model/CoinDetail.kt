package android.dev.cryptotrackerapp.domain.model

import kotlinx.collections.immutable.ImmutableList

data class CoinDetail(
    var id: String?,
    var name: String?,
    var webSlug: String?,
    var categories: ImmutableList<String>,
    var description: String?,
    var imageUrl: String?,
)