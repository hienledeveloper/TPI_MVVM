package hienle.developer.tpi_mvvm.core.preference

import android.content.Context
import android.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Hien on 5/10/24
 */
@Singleton
class UserSharedPreferences @Inject constructor(@ApplicationContext context: Context) {
    val prefs = PreferenceManager.getDefaultSharedPreferences(context)

    fun getLanguage(): String {
        return prefs.getString(PreferenceKey.LANGUAGE, "ja") ?: "ja"
    }

    fun setLanguage(lang: String) {
        prefs.edit().putString(PreferenceKey.LANGUAGE, lang).apply()
    }
}