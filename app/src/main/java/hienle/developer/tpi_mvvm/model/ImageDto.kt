package hienle.developer.tpi_mvvm.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Created by Hien on 5/10/24
 */
@Parcelize
data class ImageDto(
    @SerializedName("src")
    val src: String? = null,
    @SerializedName("ext")
    val ext: String? = null,
) : Parcelable {
    val imageUrl: String
        get() = src + ext
}