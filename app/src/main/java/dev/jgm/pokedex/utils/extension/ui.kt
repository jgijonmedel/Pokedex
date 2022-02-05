package dev.jgm.pokedex.utils.extension

import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import dev.jgm.pokedex.R
import dev.jgm.pokedex.data.model.PokemonEggGroup
import dev.jgm.pokedex.data.model.PokemonType
import dev.jgm.pokedex.utils.ThemeMode

fun ImageView.loadImage(
    url: String,
    @DrawableRes idPlaceholder: Int = R.drawable.ic_circular_progress_light,
    onFinish: (Int) -> Unit = {}
) {
    val uri = Uri.parse(url)
    Glide.with(this.context)
        .load(uri)
        .placeholder(idPlaceholder)
        .centerCrop()
        .into(this)
}

fun ViewGroup.removeViews() {
    if (this.childCount > 0) {
        this.removeAllViews()
        this.requestLayout()
        this.invalidate()
    }
}

fun ChipGroup.addType(pokemonType: PokemonType) {
    val colorLight = ContextCompat.getColor(context, pokemonType.colorLight)
    val colorDark = ContextCompat.getColor(context, pokemonType.colorLight)
    val color = if (ThemeMode.isDark(context)) colorDark else colorLight

    val chip = Chip(context).apply {
        text = context.getString(pokemonType.text)
        textEndPadding = resources.getDimension(R.dimen.separation_small)
        chipIcon = ContextCompat.getDrawable(context, pokemonType.icon)
        chipIcon?.setTint(color)
        chipBackgroundColor =
            ColorStateList.valueOf(Color.argb(45, color.red, color.green, color.blue))
        setTextAppearance(R.style.text_subtitle)
        setTextColor(color)
        isClickable = false
        isFocusable = false
        isCheckable = false
    }
    addView(chip)
}

fun ChipGroup.addEggGroup(eggGroup: PokemonEggGroup) {
    val colorSurface = context.getColorFromAttr(R.attr.colorSurface)
    val colorStroke = ContextCompat.getColor(context, R.color.shimmer_placeholder)

    val chip = Chip(context).apply {
        text = context.getString(eggGroup.text)
        textEndPadding = resources.getDimension(R.dimen.separation_small)
        chipBackgroundColor = ColorStateList.valueOf(colorSurface)
        chipStrokeColor = ColorStateList.valueOf(colorStroke)
        setTextAppearance(R.style.text_body)
        setTextColor(context.getColorFromAttr(R.attr.colorOnPrimary))
        isClickable = false
        isFocusable = false
        isCheckable = false
    }
    addView(chip)
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}