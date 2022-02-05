package dev.jgm.pokedex.data.model.pokemon

import dev.jgm.pokedex.data.model.Value
import dev.jgm.pokedex.data.model.move.VersionGroupDetail

data class MoveValue(
    val move: Value,
    val version_group_details: List<VersionGroupDetail>
)
