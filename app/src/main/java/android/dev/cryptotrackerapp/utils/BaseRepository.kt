package android.dev.cryptotrackerapp.utils

import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class BaseRepository {

    suspend fun <T, S> doNetworkRequest(mapper : (T) -> S,request: suspend () -> Response<T>): AppResponse<S> {
        return try {
            request().let {
                if (it.isSuccessful && it.body() != null) {
                    AppResponse.Success(mapper(it.body()!!))
                } else {
                    AppResponse.Error(AppError.Api(it.message()))
                }
            }
        } catch (e: DataException) {
            AppResponse.Error(AppError.Unexpected(e.message))
        } catch (e: HttpException) {
            AppResponse.Error(AppError.Unexpected(String.format("%d %s", e.code(), e.message())))
        } catch (e: IOException) {
            AppResponse.Error(AppError.Unexpected(e.message ?: "Occured Error"))
        }

    }

}

interface DataMapper<T, S> {
    fun T.mapToDomain(): S
}

fun <T : DataMapper<T, S>, S> T.mapToDomain() = this.mapToDomain()