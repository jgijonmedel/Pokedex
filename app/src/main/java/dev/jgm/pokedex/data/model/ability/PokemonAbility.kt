package dev.jgm.pokedex.data.model.ability

import dev.jgm.pokedex.data.model.Value

data class PokemonAbility(
    val is_hidden: Boolean,
    val pokemon: Value,
    val slot: Int
)
