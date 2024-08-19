package android.dev.cryptotrackerapp.data

import android.dev.cryptotrackerapp.domain.model.CoinModel
import android.dev.cryptotrackerapp.utils.mapToDomain
import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException

class CoinPageSource(
    private val cryptoApi: CryptoApi,
    private val query: String

): PagingSource<Int, CoinModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CoinModel> {
        if(query.isEmpty()) {
            return LoadResult.Page(
                emptyList(),
                prevKey = null,
                nextKey = null
            )
        }
        val page: Int = params.key ?: 1
        val pageSize: Int = params.loadSize.coerceAtMost(20)

        val response = cryptoApi.getCoinsMarkets(query, page, pageSize)

        if(response.isSuccessful) {
            val coinModel = checkNotNull(response.body()).map { it.mapToDomain() }
            val nextKey = if(coinModel.size < pageSize) null else page + 1
            val prevKey = if(page == 1) null else page - 1
            return LoadResult.Page(coinModel, prevKey, nextKey)
        } else {
            return LoadResult.Error(HttpException(response))
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CoinModel>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

}