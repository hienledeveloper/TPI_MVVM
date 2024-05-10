package hienle.developer.tpi_mvvm.util

import android.content.res.Resources
import android.util.TypedValue
import android.widget.ImageView
import com.bumptech.glide.Glide
import hienle.developer.tpi_mvvm.R

/**
 * Created by Hien on 5/10/24
 */
fun ImageView.loadUrl(url: String?) {
    if (!url.isNullOrBlank()) {
        Glide.with(this).load(url).error(R.drawable.img_page_banner_rainbow_tw).into(this)
    }
}

fun Int.dp2Px() =
    TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics,
    ).toInt()