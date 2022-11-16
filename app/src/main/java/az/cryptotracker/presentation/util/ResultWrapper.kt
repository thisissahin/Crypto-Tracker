package az.cryptotracker.presentation.util

sealed class ResultWrapper<out T> {
    data class Success<out T>(val data: T?) : ResultWrapper<T>()
    data class Error<out T>(val error: String) : ResultWrapper<T>()
    class Loading<T>(data: T? = null) : ResultWrapper<T>()
}