package dev.jgm.pokedex.utils

import android.content.Context
import android.content.res.Configuration

class ThemeMode {
    companion object {

        fun isDark(context: Context):Boolean {
            return when (context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
                Configuration.UI_MODE_NIGHT_YES -> true
                Configuration.UI_MODE_NIGHT_NO -> false
                else -> false
            }
        }
    }

}