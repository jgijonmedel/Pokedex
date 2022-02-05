package dev.jgm.pokedex.data.model.pokemon

import dev.jgm.pokedex.data.model.Value

data class AbilityValue(
    val ability: Value,
    val is_hidden: Boolean,
    val slot: Int
)