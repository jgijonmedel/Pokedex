package dev.jgm.pokedex.utils.extension

import android.content.Context
import androidx.annotation.AttrRes
import java.lang.Exception
import java.util.*

fun String.getIdFromUrl(): String {
    return if (this.endsWith("/")) {
        this.dropLast(1).takeLastWhile { it.isDigit() }
    } else {
        this.takeLastWhile { it.isDigit() }
    }
}

fun Context.getColorFromAttr(@AttrRes attrColor: Int): Int {
    val typedArray = theme.obtainStyledAttributes(intArrayOf(attrColor))
    val intColor = typedArray.getColor(0, 0)
    typedArray.recycle()
    return intColor
}

fun String.getColor(context: Context): Int {
    return context
        .resources
        .getIdentifier(
            this,
            "color",
            context.packageName
        )
}
fun String.firstCapitalLetter(): String {
    return this.replaceFirstChar {
        if (it.isLowerCase()) {
            it.titlecase(Locale.ROOT)
        } else {
            it.toString()
        }
    }
}

fun String.removeLineBreaks(): String {
    return this.replace('\n', ' ')
}

fun String.formatToUppercase(): String {
    return this.trim().uppercase().replace('-', '_')
}

fun Double.calculateDimen(): String {
    return String.format("%.1f", (this/10))
}


fun Int.formatDisplay(): String {
    return try {
        when (this.toString().length) {
            1 -> "#00$this"
            2 -> "#0$this"
            else -> "#$this"
        }
    } catch (ex: Exception) {
        this.toString()
    }
}

fun String.formatDisplay(): String {
    return try {
        when (this.length) {
            1 -> "#00$this"
            2 -> "#0$this"
            else -> "#$this"
        }
    } catch (ex: Exception) {
        this
    }
}