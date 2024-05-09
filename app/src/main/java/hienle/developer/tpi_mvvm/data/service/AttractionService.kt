package hienle.developer.tpi_mvvm.data.service

import hienle.developer.tpi_mvvm.domain.model.GenericItemsResponse
import hienle.developer.tpi_mvvm.model.AttractionDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Hien on 5/9/24
 */
interface AttractionService {

    @GET("{lang}/Attractions/All")
    suspend fun getAttractions(@Path("lang") lang: String, @Query("page") page: Int):
            Response<GenericItemsResponse<AttractionDto>>
}