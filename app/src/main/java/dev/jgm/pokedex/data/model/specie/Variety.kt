package dev.jgm.pokedex.data.model.specie

import dev.jgm.pokedex.data.model.Value

data class Variety(
    val is_default: Boolean,
    val pokemon: Value
)