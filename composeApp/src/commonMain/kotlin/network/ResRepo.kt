package network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlin.coroutines.CoroutineContext

open class ResRepo {
    fun <T> loadResourceFlow(coroutineContext: CoroutineContext = Dispatchers.IO, query: suspend () -> T): Flow<Result<T>> = flow {
        try {
            emit(Result.Success(query()))
        } catch (exception: Exception) {
            emit(Result.Error(exception.message))
        }
    }.flowOn(coroutineContext)
        .onStart { emit(Result.Loading()) }

}

sealed class Result<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?) : Result<T>(data = data)
    class Loading<T> : Result<T>()
    class Error<T>(message: String?) : Result<T>(message = message)
}