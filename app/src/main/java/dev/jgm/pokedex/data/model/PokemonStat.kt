package dev.jgm.pokedex.data.model

import dev.jgm.pokedex.R
import dev.jgm.pokedex.databinding.ItemStatListBinding

enum class PokemonStat(val text: Int) {
        HP(text = R.string.hp),
        ATTACK(text = R.string.attack),
        DEFENSE(text = R.string.defense),
        SPECIAL_ATTACK(text = R.string.special_attack),
        SPECIAL_DEFENSE(text = R.string.defense),
        SPEED(text = R.string.speed),
        ACCURACY(text = R.string.accuracy),
        EVASION(text = R.string.evasion)
}