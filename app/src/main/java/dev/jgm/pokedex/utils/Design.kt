package dev.jgm.pokedex.utils

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.palette.graphics.Palette

object Design {

    fun calcDominantColor(drawable: Drawable, onFinish: (Int) -> Unit) {
        val bmp = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)

        Palette.from(bmp).generate { palette ->
            palette?.getDominantColor(0x000000)
            palette?.dominantSwatch?.rgb?.let { colorValue ->
                onFinish(colorValue)
            }
        }
    }
}