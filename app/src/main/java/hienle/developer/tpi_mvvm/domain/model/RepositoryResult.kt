package hienle.developer.tpi_mvvm.domain.model

/**
 * Created by Hien on 5/9/24
 */
sealed class RepositoryResult<T> {
    data class Success<T>(
        val `data`: T?,
    ) : RepositoryResult<T>()

    data class Error<T>(val error: Exception) : RepositoryResult<T>()
}