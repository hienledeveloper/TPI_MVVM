package hienle.developer.tpi_mvvm.core.preference

import androidx.annotation.StringDef

/**
 * Created by Hien on 5/10/24
 */
@Retention(AnnotationRetention.SOURCE)
@StringDef(
    PreferenceKey.LANGUAGE
)
annotation class PreferenceKey {
    companion object {
        const val LANGUAGE = "language"
    }
}