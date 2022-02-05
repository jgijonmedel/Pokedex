package dev.jgm.pokedex.data.model.pokemon

import dev.jgm.pokedex.data.model.Value
import java.io.Serializable

data class Stat(
    val base_stat: Int,
    val effort: Int,
    val stat: Value
)