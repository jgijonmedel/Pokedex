package dev.jgm.pokedex.data.model.evolution

import dev.jgm.pokedex.data.model.Value

data class Chain(
    val evolves_to: List<Chain>,
    val is_baby: Boolean,
    val species: Value
)