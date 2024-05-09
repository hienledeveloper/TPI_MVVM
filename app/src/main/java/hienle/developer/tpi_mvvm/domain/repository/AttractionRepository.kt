package hienle.developer.tpi_mvvm.domain.repository

import hienle.developer.tpi_mvvm.domain.model.GenericItemsResponse
import hienle.developer.tpi_mvvm.domain.model.RepositoryResult
import hienle.developer.tpi_mvvm.model.AttractionDto
import hienle.developer.tpi_mvvm.model.request.AttractionsRequest

/**
 * Created by Hien on 5/9/24
 */
interface AttractionRepository {

    suspend fun getAttractions(request: AttractionsRequest): RepositoryResult<GenericItemsResponse<AttractionDto>>

}