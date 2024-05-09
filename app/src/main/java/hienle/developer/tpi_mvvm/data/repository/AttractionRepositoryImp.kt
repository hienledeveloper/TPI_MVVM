package hienle.developer.tpi_mvvm.data.repository

import hienle.developer.tpi_mvvm.data.service.AttractionService
import hienle.developer.tpi_mvvm.domain.model.GenericItemsResponse
import hienle.developer.tpi_mvvm.domain.model.RepositoryResult
import hienle.developer.tpi_mvvm.domain.repository.AttractionRepository
import hienle.developer.tpi_mvvm.domain.repository.BaseRepository
import hienle.developer.tpi_mvvm.model.AttractionDto
import hienle.developer.tpi_mvvm.model.request.AttractionsRequest
import javax.inject.Inject

/**
 * Created by Hien on 5/9/24
 */
class AttractionRepositoryImp @Inject constructor(private val service: AttractionService) : BaseRepository(),
    AttractionRepository {
    override suspend fun getAttractions(request: AttractionsRequest):
            RepositoryResult<GenericItemsResponse<AttractionDto>> {
        return executeService { service.getAttractions(request.lang, request.page) }
    }
}