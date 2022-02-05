package dev.jgm.pokedex.data.model.move

import dev.jgm.pokedex.data.model.Value

data class VersionGroupDetail(
    val level_learned_at: Int,
    val move_learn_method: Value,
    val version_group: Value
)