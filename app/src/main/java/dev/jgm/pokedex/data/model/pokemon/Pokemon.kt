package dev.jgm.pokedex.data.model.pokemon

import dev.jgm.pokedex.data.model.Value

data class Pokemon(
    val abilities: List<AbilityValue>,
    val base_experience: Int,
    val height: Int,
    val id: Int,
    val moves: List<MoveValue>,
    val name: String,
    val species: Value,
    val sprites: Sprites,
    val stats: List<Stat>,
    val types: List<TypeValue>,
    val weight: Int
)