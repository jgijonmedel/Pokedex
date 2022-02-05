package dev.jgm.pokedex.data.model.specie

import dev.jgm.pokedex.data.model.FlavorTextEntry
import dev.jgm.pokedex.data.model.Name
import dev.jgm.pokedex.data.model.Value

data class Specie(
    val base_happiness: Int,
    val capture_rate: Int,
    val color: Value,
    val egg_groups: List<Value>,
    val evolution_chain: EvolutionChainValue,
    val evolves_from_species: Value,
    val flavor_text_entries: List<FlavorTextEntry>,
    val forms_switchable: Boolean,
    val gender_rate: Int,
    val genera: List<Genera>,
    val growth_rate: Value,
    val habitat: Value?,
    val has_gender_differences: Boolean,
    val hatch_counter: Int,
    val id: Int,
    val is_baby: Boolean,
    val is_legendary: Boolean,
    val is_mythical: Boolean,
    val name: String,
    val names: List<Name>,
    val shape: Value,
    val order: Int,
    val varieties: List<Variety>
)