package hienle.developer.tpi_mvvm.data.usecase

import hienle.developer.tpi_mvvm.domain.model.BaseFlowUseCase
import hienle.developer.tpi_mvvm.domain.model.GenericItemsResponse
import hienle.developer.tpi_mvvm.domain.model.RepositoryResult
import hienle.developer.tpi_mvvm.domain.model.UseCaseResult
import hienle.developer.tpi_mvvm.domain.repository.AttractionRepository
import hienle.developer.tpi_mvvm.model.AttractionDto
import hienle.developer.tpi_mvvm.model.request.AttractionsRequest
import javax.inject.Inject

/**
 * Created by Hien on 5/9/24
 */
class GetAttractionUseCase @Inject constructor(private val repository: AttractionRepository) :
    BaseFlowUseCase<AttractionsRequest, GenericItemsResponse<AttractionDto>, GenericItemsResponse<AttractionDto>>() {
    override suspend fun execute(parameters: AttractionsRequest): RepositoryResult<GenericItemsResponse<AttractionDto>> {
        return repository.getAttractions(parameters)
    }

    override suspend fun onSuccess(response: RepositoryResult.Success<GenericItemsResponse<AttractionDto>>):
            UseCaseResult.Success<GenericItemsResponse<AttractionDto>> {
        return UseCaseResult.Success(response.data ?: GenericItemsResponse())
    }
}