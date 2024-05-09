package hienle.developer.tpi_mvvm.model


import com.google.gson.annotations.SerializedName

data class AttractionDto(
    @SerializedName("address")
    val address: String? = null,
    @SerializedName("category")
    val category: List<Category?>? = null,
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
    @SerializedName("files")
    val files: List<Any?>? = null,
    @SerializedName("friendly")
    val friendly: List<Any?>? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("images")
    val images: List<Any?>? = null,
    @SerializedName("introduction")
    val introduction: String? = null,
    @SerializedName("links")
    val links: List<Any?>? = null,
    @SerializedName("modified")
    val modified: String? = null,
    @SerializedName("months")
    val months: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("name_zh")
    val nameZh: Any? = null,
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
    @SerializedName("service")
    val service: List<Service?>? = null,
    @SerializedName("staytime")
    val staytime: String? = null,
    @SerializedName("target")
    val target: List<Target?>? = null,
    @SerializedName("tel")
    val tel: String? = null,
    @SerializedName("ticket")
    val ticket: String? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("zipcode")
    val zipcode: String? = null
)