package hienle.developer.tpi_mvvm.model


import com.google.gson.annotations.SerializedName

data class Target(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null
)