package hienle.developer.tpi_mvvm.domain.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Hien on 5/9/24
 */
class GenericItemsResponse<T> {

    @SerializedName("total")
    val total: Int = 0

    @SerializedName("data")
    val data: List<T> = emptyList()
}