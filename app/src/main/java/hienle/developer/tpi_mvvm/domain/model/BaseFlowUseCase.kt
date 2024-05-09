package hienle.developer.tpi_mvvm.domain.model

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach

/**
 * Created by Hien on 5/9/24
 */
abstract class BaseFlowUseCase<in P, Res, R> {
    operator fun invoke(parameters: P): Flow<UseCaseResult<R>> =
        flow {
            emit(UseCaseResult.Loading)

            when (val apiResponse = execute(parameters)) {
                is RepositoryResult.Success -> emit(onSuccess(apiResponse))
                is RepositoryResult.Error -> emit(onError(apiResponse.error))
            }
        }.catch { e -> emit(UseCaseResult.ErrorThrowable(e)) }.flowOn(Dispatchers.IO)

    protected abstract suspend fun execute(parameters: P): RepositoryResult<Res>

    abstract suspend fun onSuccess(response: RepositoryResult.Success<Res>): UseCaseResult.Success<R>

    open suspend fun onError(ex: Exception): UseCaseResult.ErrorThrowable {
        return UseCaseResult.ErrorThrowable(ex)
    }
}

sealed class UseCaseResult<out T> {
    data class Success<out T>(val data: T) : UseCaseResult<T>()
    data class ErrorThrowable(val error: Throwable) : UseCaseResult<Nothing>()
    object Loading : UseCaseResult<Nothing>()
}

fun <T> Flow<UseCaseResult<T>>.onSuccess(block: (T) -> Unit) =
    onEach {
        if (it is UseCaseResult.Success) {
            block(it.data)
        }
    }

fun <T> Flow<UseCaseResult<T>>.onThrowable(block: (Throwable) -> Unit) =
    onEach {
        if (it is UseCaseResult.ErrorThrowable) {
            block(it.error)
        }
    }