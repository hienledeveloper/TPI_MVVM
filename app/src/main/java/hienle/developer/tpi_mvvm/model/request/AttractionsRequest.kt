package hienle.developer.tpi_mvvm.model.request

import com.google.gson.annotations.SerializedName

/**
 * Created by Hien on 5/9/24
 */
data class AttractionsRequest(
    @SerializedName("page")
    val page: Int = 1,

    @SerializedName("lang")
    val lang: String = "en"
)