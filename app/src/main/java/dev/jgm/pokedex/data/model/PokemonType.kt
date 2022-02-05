package dev.jgm.pokedex.data.model

import dev.jgm.pokedex.R

enum class PokemonType(
    val text: Int,
    val colorLight: Int,
    val colorDark: Int,
    val icon: Int
) {
    BUG(
        colorLight = R.color.type_bug_light,
        colorDark = R.color.type_bug_dark,
        text = R.string.type_bug,
        icon = R.drawable.type_bug
    ),
    DARK(
        colorLight = R.color.type_dark_light,
        colorDark = R.color.type_dark_dark,
        text = R.string.type_dark,
        icon = R.drawable.type_dark
    ),
    DRAGON(
        colorLight = R.color.type_dragon_light,
        colorDark = R.color.type_dragon_dark,
        text = R.string.type_dragon,
        icon = R.drawable.type_dragon
    ),
    ELECTRIC(
        colorLight = R.color.type_electric_light,
        colorDark = R.color.type_electric_dark,
        text = R.string.type_electric,
        icon = R.drawable.type_electric
    ),
    FAIRY(
        colorLight = R.color.type_fairy_light,
        colorDark = R.color.type_fairy_dark,
        text = R.string.type_fairy,
        icon = R.drawable.type_fairy
    ),
    FIGHTING(
        colorLight = R.color.type_fighting_light,
        colorDark = R.color.type_fighting_dark,
        text = R.string.type_fighting,
        icon = R.drawable.type_fighting
    ),
    FIRE(
        colorLight = R.color.type_fire_light,
        colorDark = R.color.type_fire_dark,
        text = R.string.type_fire,
        icon = R.drawable.type_fire
    ),
    FLYING(
        colorLight = R.color.type_flying_light,
        colorDark = R.color.type_flying_dark,
        text = R.string.type_flying,
        icon = R.drawable.type_flying
    ),
    GHOST(
        colorLight = R.color.type_ghost_light,
        colorDark = R.color.type_ghost_dark,
        text = R.string.type_ghost,
        icon = R.drawable.type_ghost
    ),
    GRASS(
        colorLight = R.color.type_grass_light,
        colorDark = R.color.type_grass_dark,
        text = R.string.type_grass,
        icon = R.drawable.type_grass
    ),
    GROUND(
        colorLight = R.color.type_ground_light,
        colorDark = R.color.type_ground_dark,
        text = R.string.type_ground,
        icon = R.drawable.type_ground
    ),
    ICE(
        colorLight = R.color.type_ice_light,
        colorDark = R.color.type_ice_dark,
        text = R.string.type_ice,
        icon = R.drawable.type_ice
    ),
    NORMAL(
        colorLight = R.color.type_normal_light,
        colorDark = R.color.type_normal_dark,
        text = R.string.type_normal,
        icon = R.drawable.type_normal
    ),
    POISON(
        colorLight = R.color.type_poison_light,
        colorDark = R.color.type_poison_dark,
        text = R.string.type_poison,
        icon = R.drawable.type_poison
    ),
    PSYCHIC(
        colorLight = R.color.type_psychic_light,
        colorDark = R.color.type_psychic_dark,
        text = R.string.type_psychic,
        icon = R.drawable.type_psychic
    ),
    ROCK(
        colorLight = R.color.type_rock_light,
        colorDark = R.color.type_rock_dark,
        text = R.string.type_rock,
        icon = R.drawable.type_rock
    ),
    STEEL(
        colorLight = R.color.type_steel_light,
        colorDark = R.color.type_steel_dark,
        text = R.string.type_steel,
        icon = R.drawable.type_steel
    ),
    WATER(
        colorLight = R.color.type_water_light,
        colorDark = R.color.type_water_dark,
        text = R.string.type_water,
        icon = R.drawable.type_water
    )
}