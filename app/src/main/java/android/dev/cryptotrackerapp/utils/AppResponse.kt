package android.dev.cryptotrackerapp.utils

sealed class AppResponse<out T> {
    data class Success<T>(val value: T): AppResponse<T>()
    data class Error(val error: AppError): AppResponse<Nothing>()

    suspend fun onSuccess(block: suspend (T) -> Unit) : AppResponse<T> {
        if(this is Success) block(value)
        return this
    }

    suspend fun onError(block: suspend (AppError) -> Unit) : AppResponse<T> {
        if(this is Error) block(error)
        return this
    }
}

sealed class AppError {

    abstract val message: String

    class Api(override val message: String) : AppError()
    class Unexpected(override val message: String) : AppError()
}

abstract class DataException : RuntimeException() {
    abstract override val message: String
}