package hienle.developer.tpi_mvvm.ui.component

import android.view.View
import androidx.viewpager.widget.ViewPager
import hienle.developer.tpi_mvvm.R
import kotlin.math.abs

/**
 * Created by Hien on 5/10/24
 */
class AlphaAndScalePageTransformer : ViewPager.PageTransformer {
    companion object {
        private const val SCALE_MAX = 0.9f
        private const val ALPHA_MAX = 0.4f
    }

    override fun transformPage(
        page: View,
        position: Float,
    ) {
        val flAlpha = page.findViewById<View>(R.id.flBlur)
        val scale = if (position < 0) (1 - SCALE_MAX) * position + 1 else (SCALE_MAX - 1) * position + 1
        val alpha = if (position < 0) (1 - ALPHA_MAX) * position + 1 else (ALPHA_MAX - 1) * position + 1
        if (position < 0) {
            page.pivotX = page.width.toFloat()
            page.pivotY = (page.height / 2).toFloat()
        } else {
            page.pivotX = 0f
            page.pivotY = (page.height / 2).toFloat()
        }
        page.scaleX = scale
        page.scaleY = scale
        flAlpha.alpha = abs(1 - alpha)
    }
}