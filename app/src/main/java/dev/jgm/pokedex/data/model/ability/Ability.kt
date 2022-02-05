package dev.jgm.pokedex.data.model.ability

import dev.jgm.pokedex.data.model.EffectEntries
import dev.jgm.pokedex.data.model.FlavorTextEntry
import dev.jgm.pokedex.data.model.Name

data class Ability(
    val id: Int,
    val effect_entries: List<EffectEntries>,
    val flavor_text_entries: List<FlavorTextEntry>,
    val name: String,
    val names: List<Name>,
    val pokemon: List<PokemonAbility>
)