package az.cryptotracker.data.base

import az.cryptotracker.presentation.util.ResultWrapper
import retrofit2.Response

open class BaseNetworkRepository(
) {

    companion object {
        const val GENERAL_ERROR_MESSAGE = "Something went wrong. Please try again."
    }

    suspend fun <T> apiCallInternal(
        apiCall: suspend () -> Response<T>?
    ): ResultWrapper<Response<T>> {
        return try {
            val result = apiCall()
            if (result?.isSuccessful == true) {
                ResultWrapper.Success(result)
            } else {
                val errorBody = result?.errorBody()?.toString() ?: GENERAL_ERROR_MESSAGE
                ResultWrapper.Error(errorBody)
            }
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            ResultWrapper.Error(GENERAL_ERROR_MESSAGE)
            /*when (throwable) {

            }*/
        }
    }


    suspend inline fun <reified T> handleNetwork(
        crossinline apiCall: suspend () -> Response<T>
    ): ResultWrapper<Response<T>> {
        val wrapper = apiCallInternal {
            apiCall()
        }
        return wrapper
    }

}