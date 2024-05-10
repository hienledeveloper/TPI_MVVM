package hienle.developer.tpi_mvvm.util

import android.graphics.Typeface
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

/**
 * Created by Hien on 5/10/24
 */
object BindingAdapterUtil {

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(view: ImageView, imageUrl: String?) {
        view.loadUrl(imageUrl)
    }

    @BindingAdapter("boldTextStyle")
    @JvmStatic
    fun changeTextStyle(view: TextView, hasBoldText: Boolean) {
        view.typeface = if (hasBoldText) Typeface.DEFAULT_BOLD else Typeface.DEFAULT
    }

    @BindingAdapter("display")
    @JvmStatic
    fun display(view: View, isShow: Boolean) {
        view.isVisible = isShow
    }

}