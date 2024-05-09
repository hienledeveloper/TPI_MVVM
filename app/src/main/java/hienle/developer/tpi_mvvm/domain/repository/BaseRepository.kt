package hienle.developer.tpi_mvvm.domain.repository

import hienle.developer.tpi_mvvm.domain.model.RepositoryResult
import retrofit2.HttpException
import retrofit2.Response

/**
 * Created by Hien on 5/9/24
 */
abstract class BaseRepository {

    suspend fun <_Response> executeService(api: suspend () -> Response<_Response>): RepositoryResult<_Response> {
        try {
            api.invoke().run {
                body()?.let { res ->
                    if (isSuccessful) {
                        return RepositoryResult.Success(body())
                    }
                }
                return RepositoryResult.Error(error = HttpException(this))
            }
        } catch (ex: Exception) {
            // Unknown
            return RepositoryResult.Error(ex)
        }
    }

}