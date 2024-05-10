package hienle.developer.tpi_mvvm.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AttractionDto(
    @SerializedName("address")
    val address: String? = null,
    @SerializedName("category")
    val category: List<CategoryDto>? = null,
    @SerializedName("distric")
    val distric: String? = null,
    @SerializedName("elong")
    val elong: Double? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("facebook")
    val facebook: String? = null,
    @SerializedName("fax")
    val fax: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("images")
    val images: List<ImageDto>? = null,
    @SerializedName("introduction")
    val introduction: String? = null,
    @SerializedName("modified")
    val modified: String? = null,
    @SerializedName("months")
    val months: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("nlat")
    val nlat: Double? = null,
    @SerializedName("official_site")
    val officialSite: String? = null,
    @SerializedName("open_status")
    val openStatus: Int? = null,
    @SerializedName("open_time")
    val openTime: String? = null,
    @SerializedName("remind")
    val remind: String? = null,
    @SerializedName("staytime")
    val staytime: String? = null,
    @SerializedName("tel")
    val tel: String? = null,
    @SerializedName("ticket")
    val ticket: String? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("zipcode")
    val zipcode: String? = null
) : Parcelable {
    val imageUrl: String?
        get() = images?.map { it.imageUrl }?.getOrNull(0)
}