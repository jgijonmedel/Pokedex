package dev.jgm.pokedex.data.model

import dev.jgm.pokedex.R

enum class PokemonColor(val color: Int, val text: Int) {
    BLACK(R.color.black, R.string.black),
    BLUE(R.color.blue, R.string.blue),
    BROWN(R.color.brown, R.string.brown),
    GRAY(R.color.gray, R.string.gray),
    GREEN(R.color.green, R.string.green),
    PINK(R.color.pink, R.string.pink),
    PURPLE(R.color.purple, R.string.purple),
    RED(R.color.red, R.string.red),
    WHITE(R.color.white, R.string.white),
    YELLOW(R.color.yellow, R.string.yellow)
}